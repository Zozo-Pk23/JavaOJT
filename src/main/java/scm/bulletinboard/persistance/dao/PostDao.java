package scm.bulletinboard.persistance.dao;

import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.web.form.PostForm;

import java.util.Date;
import java.util.List;

public interface PostDao {
    List<Post> getAllPosts();
    public void savePost(PostForm post, Date currentDate);
    public Post getPostById(Long postId);
    public void updatePost(Post post);
    public void deletePost(Post post);
}
