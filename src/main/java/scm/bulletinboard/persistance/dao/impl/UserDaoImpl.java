package scm.bulletinboard.persistance.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import scm.bulletinboard.persistance.dao.UserDao;
import scm.bulletinboard.persistance.entity.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM User WHERE deletedAt IS NULL";
        return session.createQuery(query, User.class).getResultList();
    }
}
