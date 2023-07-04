package scm.bulletinboard.persistance.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import scm.bulletinboard.persistance.dao.PostDao;
import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.web.form.PostForm;

import java.util.Date;
import java.util.List;

@Repository
public class PostDaoImpl implements PostDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public PostDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<Post> getAllPosts() {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Post WHERE deletedAt IS NULL";
        return session.createQuery(query, Post.class).getResultList();
    }

    @Override
    public void savePost(PostForm postForm, Date currentDate) {
        Post post = new Post(postForm);
        post.setStatus("1");
        post.setCreatedAt(currentDate);
        post.setCreatedUserId(1);
        this.sessionFactory.getCurrentSession().save(post);
    }

    @Override
    public Post getPostById(Long postId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Post.class, postId);
    }
    @Override
    public void updatePost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.update(post);
    }

    @Override
    public void deletePost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.update(post);
    }

    @Override
    public void upload(Post post){
        Session session = sessionFactory.getCurrentSession();
        session.save(post);
    }
}
