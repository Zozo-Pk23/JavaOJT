package scm.bulletinboard.persistance.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import scm.bulletinboard.bl.dto.PostDto;
import scm.bulletinboard.persistance.dao.PostDao;
import scm.bulletinboard.persistance.entity.Post;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.PostForm;

import java.util.ArrayList;
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
    public List<PostDto> getAllPosts(int pageNumber, int pageSize, String searchQuery, User user) {
        String userType = user.getType();
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT p, u.name , u2.name FROM Post p LEFT JOIN User u ON p.createdUserId = u.id "
                + "LEFT JOIN User u2 ON  p.updatedUserId = u2.id WHERE p.deletedAt IS NULL";
        if (searchQuery != null && !searchQuery.isEmpty()) {
            hql += " AND (p.title LIKE :searchQuery OR p.description LIKE :searchQuery)";
        }
        if (userType.equals("1")) {
            hql += " AND p.createdUserId = :userId";
        }
        Query<Object[]> query = session.createQuery(hql);
        if (userType.equals("1")) {
            query.setParameter("userId", user.getId());
        }
        if (searchQuery != null && !searchQuery.isEmpty()) {
            query.setParameter("searchQuery", "%" + searchQuery + "%");
        }
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        List<Object[]> results = query.getResultList();
        List<PostDto> posts = new ArrayList<>();
        for (Object[] result : results) {
            Post post = (Post) result[0];
            String createdUserName = (String) result[1];
            String updatedUserName = (String) result[2];
            PostDto PostDto = new PostDto(post, createdUserName, updatedUserName);
            posts.add(PostDto);
        }
        return posts;
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
    public void savePost(PostForm postForm, Date currentDate, Long userId) {
        Post post = new Post(postForm);
        int id = userId.intValue();
        post.setStatus("1");
        post.setCreatedAt(currentDate);
        post.setUpdatedAt(currentDate);
        post.setCreatedUserId(id);
        post.setUpdatedUserId(id);
        this.sessionFactory.getCurrentSession().save(post);
    }

    @Override
    public Post getPostById(Long postId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Post.class, postId);
    }

    @Override
    public void updatePost(Post post, Date currentDate, Long userId) {
        int id = userId.intValue();
        post.setUpdatedUserId(id);
        post.setUpdatedAt(currentDate);
        this.sessionFactory.getCurrentSession().update(post);
    }

    @Override
    public void deletePost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.update(post);
    }

    @Override
    public void upload(List<Post> posts) {
        Session session = sessionFactory.getCurrentSession();
        for (Post post : posts) {
            session.save(post);
        }
    }

    @Override
    public List<Post> download(User user) {
        String userType = user.getType();
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Post p";
        if (userType.equals("1")) {
            hql += " WHERE p.createdUserId = :userId";
        }
        Query<Post> query = session.createQuery(hql, Post.class);
        if (userType.equals("1")) {
            query.setParameter("userId", user.getId());
        }
        return query.getResultList();
    }

    @Override
    public Post findByTitle(String title) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Post WHERE title = :title", Post.class)
                .setParameter("title", title)
                .uniqueResult();
    }

    @Override
    public Post getPostByUserId(Long postId, User user) {
        String userType = user.getType();
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Post p WHERE p.deletedAt IS NULL AND p.id = :postId";
        if (userType.equals("1")) {
            hql += " AND p.createdUserId = :userId";
        }
        Query<Post> query = session.createQuery(hql, Post.class);
        query.setParameter("postId", postId);
        if (userType.equals("1")) {
            query.setParameter("userId", user.getId());
        }
        return query.uniqueResult();
    }

}
