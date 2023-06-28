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

    public void savePost(PostForm postForm){
        this.postDao.savePost(postForm,new Date());
    }

    public Post getPostById(Long id){
        return postDao.getPostById(id);
    }

    public void updatePost(Post post){
        this.postDao.updatePost(post);
    }

    public void deletePost(Post post){
        this.postDao.deletePost(post);
    }
}
