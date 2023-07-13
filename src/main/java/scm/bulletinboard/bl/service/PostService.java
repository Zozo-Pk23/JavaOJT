package scm.bulletinboard.bl.service;

import scm.bulletinboard.bl.dto.PostDto;
import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.PostForm;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts(int pageNumber, int pageSize, String searchQuery, User user);

    public void savePost(PostForm postForm, Long userid);

    public Post getPostById(Long id);

    public void updatePost(Post post, Long userid);

    public void deletePost(Post post);

    public List<String> upload(List<String[]> csvData, Integer id);

    int getTotalPostsCount(String searchQuery, User user);

    List<Post> download(User user);

    boolean checkIfTitleExists(String title);

    boolean uniqueEditPost(String title, Long postIdToIgnore);

    public Post getPostByUserId(Long id, User user);
}