package scm.bulletinboard.bl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import scm.bulletinboard.bl.service.PostService;
import scm.bulletinboard.persistance.dao.PostDao;
import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.web.form.PostForm;

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

    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    public void savePost(PostForm postForm) {
        this.postDao.savePost(postForm, new Date());
    }

    public Post getPostById(Long id) {
        return postDao.getPostById(id);
    }

    public void updatePost(Post post) {
        this.postDao.updatePost(post);
    }

    public void deletePost(Post post) {
        this.postDao.deletePost(post);
    }

    public void upload(List<String[]> csvData) {
        for (String[] line : csvData) {
            String title = line[0];
            String description = line[1];
            String status = line[2];
            Post post = new Post();
            post.setTitle(title);
            post.setDescription(description);
            post.setStatus(status);
            this.postDao.upload(post);
        }
    }
}
