package scm.bulletinboard.persistance.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import scm.bulletinboard.persistance.dao.UserDao;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.UserForm;

import java.util.Date;
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

    @Override
    public User getUserById(Long userId){
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, userId);
    }

    @Override
    public void saveUser(UserForm userForm,Date currentDate){
        User user=new User(userForm);
        user.setCreatedAt(currentDate);
        user.setCreatedUserId(1);
        this.sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(User user){
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void updateUser(User user){
        Session session=sessionFactory.getCurrentSession();
        session.update(user);
    }
}
