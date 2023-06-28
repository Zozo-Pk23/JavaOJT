package scm.bulletinboard.bl.service;

import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.web.form.PostForm;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    public void savePost(PostForm postForm);

    public Post getPostById(Long id);

    public void updatePost(Post post);
    public void deletePost(Post post);
}