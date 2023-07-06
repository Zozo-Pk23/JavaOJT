package scm.bulletinboard.persistance.dao;

import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.PostForm;

import java.util.Date;
import java.util.List;

public interface PostDao {
    List<Post> getAllPosts(int pageNumber, int pageSize, String searchQuery,User user);

    public void savePost(PostForm post, Date currentDate);

    public Post getPostById(Long postId);

    public void updatePost(Post post);

    public void deletePost(Post post);

    public void upload(Post post);

    int getTotalPostsCount(String searchQuery,User user);
}
