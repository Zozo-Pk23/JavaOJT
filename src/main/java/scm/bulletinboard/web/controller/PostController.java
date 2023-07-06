package scm.bulletinboard.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import scm.bulletinboard.bl.service.PostService;
import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.PostForm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        List<Post> posts = postService.getAllPosts(pageNumber, pageSize, searchQuery, user);
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
        }
        model.addAttribute("postForm", postForm);
        return "posts/confirm";
    }

    @PostMapping("/posts/save")
    public ModelAndView savePost(@ModelAttribute("postForm") PostForm postForm) {
        this.postService.savePost(postForm);
        String message = "Post Successfully Created";
        ModelAndView postListView = new ModelAndView("redirect:/posts/index?created=" + message);
        return postListView;
    }

    @GetMapping("/posts/edit")
    public ModelAndView editPost(@RequestParam("id") Long postId, Model model) {
        Post post = postService.getPostById(postId);
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
            Model model) {
        if (bindingResult.hasErrors()) {
            return "posts/edit";
        }
        if (!"1".equals(postForm.getStatus())) {
            postForm.setStatus("0");
        }
        model.addAttribute("postForm", postForm);
        return "posts/update";
    }

    @PostMapping("/posts/updateConfirm")
    public ModelAndView updateConfirmPost(@ModelAttribute("postForm") PostForm postForm) {
        Post post = postService.getPostById(postForm.getId());
        if (post != null) {
            post.setTitle(postForm.getTitle());
            post.setDescription(postForm.getDescription());
            post.setStatus(postForm.getStatus());
            postService.updatePost(post);
        }
        String message = "Post Successfully Edited";
        ModelAndView postListView = new ModelAndView("redirect:/posts/index?created=" + message);
        return postListView;
    }

    @PostMapping("/posts/delete")
    public ModelAndView deletePost(@RequestParam("id") Long postId) {
        Post post = postService.getPostById(postId);
        if (post != null) {
            post.setDeletedAt(new Date());
            postService.deletePost(post);
        }
        ModelAndView postListView = new ModelAndView("redirect:/posts/index");
        return postListView;
    }

    @GetMapping("/posts/download")
    public void downloadCSV(HttpServletResponse response) throws IOException {
        // List<Post> posts = postService.getAllPosts();
        StringBuilder csvData = new StringBuilder();
        csvData.append(
                "Id,Title,Description,Status,created_user_id,updated_user_id,deleted_user_id,created_at,updated_at,deleted_at\n");
        // for (Post post : posts) {
        // csvData.append("\"").append(post.getId()).append("\",");
        // csvData.append("\"").append(post.getTitle()).append("\",");
        // csvData.append("\"").append(post.getDescription()).append("\",");
        // csvData.append("\"").append(post.getStatus()).append("\",");
        // csvData.append("\"").append(post.getCreatedUserId()).append("\",");
        // csvData.append("\"").append(post.getUpdatedUserId()).append("\",");
        // csvData.append("\"").append(post.getDeletedUserId()).append("\",");
        // csvData.append("\"").append(post.getCreatedAt()).append("\",");
        // csvData.append("\"").append(post.getUpdatedAt()).append("\",");
        // csvData.append("\"").append(post.getDeletedAt()).append("\"\n");
        // }
        // response.setContentType("text/csv");
        // response.setHeader("Content-Disposition", "attachment;
        // filename=\"posts.csv\"");
        // response.getWriter().write(csvData.toString());
        // response.getWriter().flush();
    }

    @GetMapping("/posts/uploadForm")
    public ModelAndView uploadForm() {
        ModelAndView uploadForm = new ModelAndView("posts/upload");
        return uploadForm;
    }

    @PostMapping("/posts/upload")
    public String handleUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
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
            postService.upload(csvData, userId);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/posts/index";
    }
}
