package scm.bulletinboard.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import scm.bulletinboard.bl.dto.PostDto;
import scm.bulletinboard.bl.service.PostService;
import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.PostForm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    private int calculateTotalPages(int pageSize, int totalPosts) {
        return (int) Math.ceil((double) totalPosts / pageSize);
    }

    @GetMapping("/posts/index")
    public ModelAndView postList(@RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(required = false) String searchQuery, HttpSession session) {

        User user = (User) session.getAttribute("user");
        ModelAndView postListView = new ModelAndView("posts/index");
        int pageSize = 5;
        List<PostDto> posts = postService.getAllPosts(pageNumber, pageSize, searchQuery, user);
        int totalPosts = postService.getTotalPostsCount(searchQuery, user);
        postListView.addObject("posts", posts);
        postListView.addObject("currentPage", pageNumber);
        postListView.addObject("totalPages", calculateTotalPages(pageSize, totalPosts));
        postListView.addObject("totalPosts", totalPosts);
        postListView.addObject("searchQuery", searchQuery);

        return postListView;
    }

    @GetMapping("/posts/create")
    public ModelAndView createPost(Model model) {
        ModelAndView postCreateView = new ModelAndView("posts/create");
        postCreateView.addObject("postForm", new PostForm());
        return postCreateView;
    }

    @PostMapping("/posts/add")
    public String addPost(@ModelAttribute("postForm") @Valid PostForm postForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "posts/create";
        } else if (this.postService.checkIfTitleExists(postForm.getTitle())) {
            bindingResult.rejectValue("title", "error.title", "Title is already existed");
            return "posts/create";
        }
        model.addAttribute("postForm", postForm);
        return "posts/confirm";
    }

    @PostMapping("/posts/save")
    public ModelAndView savePost(@ModelAttribute("postForm") PostForm postForm, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Long userid = user.getId();
        this.postService.savePost(postForm, userid);
        String message = "Post Successfully Created";
        ModelAndView postListView = new ModelAndView("redirect:/posts/index?created=" + message);
        return postListView;
    }

    @GetMapping("/posts/edit")
    public ModelAndView editPost(@RequestParam("id") Long postId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Post post = postService.getPostByUserId(postId, user);
        if (post == null) {
            ModelAndView postCreateView = new ModelAndView("redirect:/posts/create");
            return postCreateView;
        }
        PostForm postForm = new PostForm();
        postForm.setDescription(post.getDescription());
        postForm.setTitle(post.getTitle());
        postForm.setStatus(post.getStatus());
        postForm.setId(post.getId());
        ModelAndView postEditView = new ModelAndView("/posts/edit");
        postEditView.addObject("postForm", postForm);
        return postEditView;
    }

    @PostMapping("/posts/update")
    public String updatePost(@ModelAttribute("postForm") @Valid PostForm postForm, BindingResult bindingResult,
            Model model, @RequestParam("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "posts/edit";
        } else if (this.postService.uniqueEditPost(postForm.getTitle(), id)) {
            bindingResult.rejectValue("title", "error.title", "Title is already existed");
            return "posts/edit";
        }
        if (!"1".equals(postForm.getStatus())) {
            postForm.setStatus("0");
        }
        model.addAttribute("postForm", postForm);
        return "posts/update";
    }

    @PostMapping("/posts/updateConfirm")
    public ModelAndView updateConfirmPost(@ModelAttribute("postForm") PostForm postForm, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Long userid = user.getId();
        Post post = postService.getPostById(postForm.getId());
        if (post != null) {
            post.setTitle(postForm.getTitle());
            post.setDescription(postForm.getDescription());
            post.setStatus(postForm.getStatus());
            postService.updatePost(post, userid);
        }
        String message = "Post Successfully Edited";
        ModelAndView postListView = new ModelAndView("redirect:/posts/index?created=" + message);
        return postListView;
    }

    @PostMapping("/posts/delete")
    public ModelAndView deletePost(@RequestParam("id") Long postId, HttpSession session) {
        Post post = postService.getPostById(postId);
        User user = (User) session.getAttribute("user");
        int userid = user.getId().intValue();
        if (post != null) {
            post.setDeletedAt(new Date());
            post.setDeletedUserId(userid);
            postService.deletePost(post);
        }
        ModelAndView postListView = new ModelAndView("redirect:/posts/index");
        return postListView;
    }

    @GetMapping("/posts/download")
    public void downloadCSV(HttpServletResponse response, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        List<Post> posts = postService.download(user);
        StringBuilder csvData = new StringBuilder();
        csvData.append(
                "id,title,description,status,created_user_id,updatedd_user_id,deleted_usesr_id,deleted_at,created_at,updated_at\n");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        for (Post post : posts) {
            csvData.append("\"").append(post.getId()).append("\",");
            csvData.append("\"").append(post.getTitle()).append("\",");
            csvData.append("\"").append(post.getDescription()).append("\",");
            csvData.append("\"").append(post.getStatus()).append("\",");
            csvData.append("\"").append(post.getCreatedUserId() != null ? post.getCreatedUserId() : "").append("\",");
            csvData.append("\"").append(post.getUpdatedUserId() != null ? post.getUpdatedUserId() : "").append("\",");
            csvData.append("\"").append(post.getDeletedUserId() != null ? post.getDeletedUserId() : "").append("\",");
            csvData.append("\"").append(post.getDeletedAt() != null ? dateFormat.format(post.getDeletedAt()) : "")
                    .append("\",");
            csvData.append("\"").append(post.getCreatedAt() != null ? dateFormat.format(post.getCreatedAt()) : "")
                    .append("\",");
            csvData.append("\"").append(post.getUpdatedAt() != null ? dateFormat.format(post.getUpdatedAt()) : "")
                    .append("\"\n");
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"posts.csv\"");
        response.getWriter().write(csvData.toString());
        response.getWriter().flush();
    }

    @GetMapping("/posts/uploadForm")
    public ModelAndView uploadForm() {
        ModelAndView uploadForm = new ModelAndView("posts/upload");
        return uploadForm;
    }

    @PostMapping("/posts/upload")
    public String handleUpload(@RequestParam("file") MultipartFile file, HttpSession session, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("required", "Please choose a file");
            return "posts/upload";
        } else if (!file.getOriginalFilename().matches(".*\\.(csv)$")) {
            model.addAttribute("format", "Please choose a csv format");
            return "posts/upload";
        }
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId().intValue();
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            List<String[]> csvData = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                csvData.add(data);
            }
            List<String> errorMessages = postService.upload(csvData, userId);
            if (errorMessages.size() != 0) {
                model.addAttribute("errors", errorMessages);
                return "posts/upload";
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = "Post uploaded successfully";
        return "redirect:/posts/index?created=" + message;
    }
}
