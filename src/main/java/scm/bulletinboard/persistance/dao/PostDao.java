package scm.bulletinboard.persistance.dao;

import scm.bulletinboard.bl.dto.PostDto;
import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.PostForm;

import java.util.Date;
import java.util.List;

public interface PostDao {
    List<PostDto> getAllPosts(int pageNumber, int pageSize, String searchQuery, User user);

    public void savePost(PostForm post, Date currentDate, Long userId);

    public Post getPostById(Long postId);

    public void updatePost(Post post, Date currentDate, Long userid);

    public void deletePost(Post post);

    public void upload(Post post);

    int getTotalPostsCount(String searchQuery, User user);

    List<Post> download(User user);

    Post findByTitle(String title);

    public Post getPostByUserId(Long postId, User user);
}
