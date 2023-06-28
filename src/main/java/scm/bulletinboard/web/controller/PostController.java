package scm.bulletinboard.web.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import scm.bulletinboard.bl.service.PostService;
import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.web.form.PostForm;

import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("/posts/index")
    public ModelAndView postList(Model model) {
        ModelAndView postListView = new ModelAndView("posts/index");
        List<Post> posts=this.postService.getAllPosts();
        postListView.addObject("posts",posts);
        return postListView;
    }

    @GetMapping("/posts/create")
    public ModelAndView createPost(Model model){
        ModelAndView postCreateView = new ModelAndView("posts/create");
        postCreateView.addObject("postForm",new PostForm());
        return postCreateView;
    }

    @PostMapping("/posts/add")
    public String addPost(@ModelAttribute("postForm") @Valid PostForm postForm, BindingResult bindingResult,Model model ) {
        if (bindingResult.hasErrors()) {
            return "posts/create";
        }
        model.addAttribute("postForm", postForm);
        return "posts/confirm";
    }

    @PostMapping("/posts/save")
    public ModelAndView savePost(@ModelAttribute("postForm") PostForm postForm){
        this.postService.savePost(postForm);
        ModelAndView postListView = new ModelAndView("redirect:/posts/index");
        return postListView;
    }

    @GetMapping("/posts/edit")
    public ModelAndView editPost(@RequestParam("id") Long postId, Model model) {
        Post post = postService.getPostById(postId);
        ModelAndView postEditView = new ModelAndView("/posts/edit");
        postEditView.addObject("post", post);
        return postEditView;
    }

    @PostMapping("/posts/update")
    public String updatePost(@ModelAttribute("postForm") @Valid PostForm postForm, BindingResult bindingResult,Model model ) {
        if (bindingResult.hasErrors()) {
            return "posts/edit";
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
            postService.updatePost(post);
        }
        ModelAndView postListView = new ModelAndView("redirect:/posts/index");
        return postListView;
    }

    @PostMapping("/posts/delete")
    public ModelAndView deletePost(@RequestParam("id") Long postId){
        Post post = postService.getPostById(postId);
        if (post != null) {
            post.setDeletedAt(new Date());
            postService.deletePost(post);
        }
        ModelAndView postListView = new ModelAndView("redirect:/posts/index");
        return postListView;
    }
}
