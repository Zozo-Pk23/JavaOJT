package scm.bulletinboard.persistance.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import scm.bulletinboard.persistance.dao.PostDao;
import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.persistance.entity.User;
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
    public List<Post> getAllPosts(int pageNumber, int pageSize, String searchQuery, User user) {
        String userType = user.getType();
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Post p WHERE p.deletedAt IS NULL";
        if (searchQuery != null && !searchQuery.isEmpty()) {
            hql += " AND (p.title LIKE :searchQuery OR p.description LIKE :searchQuery)";
        }
        if (userType.equals("1")) {
            hql += " AND p.createdUserId = :userId";
        }
        Query<Post> query = session.createQuery(hql, Post.class);
        if (userType.equals("1")) {
            query.setParameter("userId", user.getId());
        }
        if (searchQuery != null && !searchQuery.isEmpty()) {
            query.setParameter("searchQuery", "%" + searchQuery + "%");
        }
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int getTotalPostsCount(String searchQuery, User user) {
        String userType = user.getType();
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT COUNT(*) FROM Post p WHERE p.deletedAt IS NULL";
        if (searchQuery != null && !searchQuery.isEmpty()) {
            hql += " AND (p.title LIKE :searchQuery OR p.description LIKE :searchQuery)";
        }
        if (userType.equals("1")) {
            hql += " AND p.createdUserId = :userId";
        }
        Query<Long> query = session.createQuery(hql, Long.class);
        if (userType.equals("1")) {
            query.setParameter("userId", user.getId());
        }
        if (searchQuery != null && !searchQuery.isEmpty()) {
            query.setParameter("searchQuery", "%" + searchQuery + "%");
        }
        return query.getSingleResult().intValue();
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
    public void upload(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.save(post);
    }
}
