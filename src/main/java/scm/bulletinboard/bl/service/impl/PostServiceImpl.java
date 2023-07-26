package scm.bulletinboard.bl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import scm.bulletinboard.bl.dto.PostDto;
import scm.bulletinboard.bl.service.PostService;
import scm.bulletinboard.persistance.dao.PostDao;
import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.PostForm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class PostServiceImpl implements PostService {
    private final PostDao postDao;

    @Autowired
    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<PostDto> getAllPosts(int pageNumber, int pageSize, String searchQuery, User user) {
        return postDao.getAllPosts(pageNumber, pageSize, searchQuery, user);
    }

    public void savePost(PostForm postForm, Long userId) {
        this.postDao.savePost(postForm, new Date(), userId);
    }

    public Post getPostById(Long id) {
        return postDao.getPostById(id);
    }

    public void updatePost(Post post, Long userId) {
        this.postDao.updatePost(post, new Date(), userId);
    }

    public void deletePost(Post post) {
        this.postDao.deletePost(post);
    }

    public List<String> upload(List<String[]> csvData, Integer id) {
        List<String> errorMessages = new ArrayList<>();
        List<Post> postsToUpload = new ArrayList<>();
        for (int lineNumber = 0; lineNumber < csvData.size(); lineNumber++) {
            String[] line = csvData.get(lineNumber);
            if (line.length >= 3) {
                String title = line[0];
                String description = line[1];
                String status = line[2];
                if (title.isEmpty()) {
                    errorMessages.add("Title is blank at line " + lineNumber);
                }
                if (description.isEmpty()) {
                    errorMessages.add("Description is blank at line " + lineNumber);
                }
                if (status.isEmpty()) {
                    errorMessages.add("Status is blank at line " + lineNumber);
                }
                if (!status.equals("0") && !status.equals("1")) {
                    errorMessages.add("Invalid status value at line " + lineNumber);
                }
                if (checkIfTitleExists(title)) {
                    errorMessages.add("Title must be unique at line " + lineNumber);
                }
                if (errorMessages.isEmpty()) {
                    Post post = new Post();
                    post.setTitle(title);
                    post.setDescription(description);
                    post.setStatus(status);
                    post.setCreatedAt(new Date());
                    post.setUpdatedAt(new Date());
                    post.setCreatedUserId(id);
                    post.setUpdatedUserId(id);
                    postsToUpload.add(post);
                }
            }
        }
        if(errorMessages.isEmpty()) {
        this.postDao.upload(postsToUpload);
        }
        return errorMessages;
    }

    public int getTotalPostsCount(String searchQuery, User user) {
        return postDao.getTotalPostsCount(searchQuery, user);
    }

    public List<Post> download(User user) {
        return postDao.download(user);
    }

    public boolean checkIfTitleExists(String title) {
        return postDao.findByTitle(title) != null;
    }

    public boolean uniqueEditPost(String title, Long postIdToIgnore) {
        Post post = postDao.findByTitle(title);
        if (post != null && !post.getId().equals(postIdToIgnore)) {
            return true;
        }
        return false;
    }

    public Post getPostByUserId(Long id, User user) {
        return postDao.getPostByUserId(id, user);
    }
}
