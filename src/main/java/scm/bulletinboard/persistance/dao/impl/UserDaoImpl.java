package scm.bulletinboard.persistance.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import scm.bulletinboard.bl.dto.UserDto;
import scm.bulletinboard.persistance.dao.UserDao;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.UserForm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserDto> getAllUsers(int pageNumber, int pageSize, User user, String searchName, String searchEmail,
            LocalDate searchStartDate, LocalDate searchEndDate) {

        String userType = user.getType();
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT u, createdUser.name, updatedUser.name FROM User u LEFT JOIN User createdUser ON u.createdUserId = createdUser.id "
                + "LEFT JOIN User updatedUser ON u.updatedUserId = updatedUser.id WHERE u.deletedAt IS NULL";

        if (userType.equals("1")) {
            hql += " AND u.createdUserId = :userId";
        }

        if (searchName != null && !searchName.isEmpty()) {
            hql += " AND u.name LIKE :name";
        }

        if (searchEmail != null && !searchEmail.isEmpty()) {
            hql += " AND u.email LIKE :email";
        }

        if (searchStartDate != null) {
            hql += " AND u.createdAt >= :startdate ";
        }

        if (searchEndDate != null) {
            hql += " AND u.createdAt <= :enddate ";
        }

        Query<Object[]> query = session.createQuery(hql);

        if (userType.equals("1")) {
            query.setParameter("userId", user.getId());
        }

        if (searchName != null && !searchName.isEmpty()) {
            query.setParameter("name", "%" + searchName + "%");
        }

        if (searchEmail != null && !searchEmail.isEmpty()) {
            query.setParameter("email", "%" + searchEmail + "%");
        }

        if (searchStartDate != null) {
            query.setParameter("startdate", searchStartDate);
        }

        if (searchEndDate != null) {
            query.setParameter("enddate", searchEndDate);
        }

        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        List<Object[]> results = query.getResultList();
        List<UserDto> users = new ArrayList<>();
        for (Object[] result : results) {
            User usera = (User) result[0];
            String createdUserName = (String) result[1];
            String updatedUserName = (String) result[2];
            UserDto userDto = new UserDto(usera, createdUserName, updatedUserName);
            users.add(userDto);
        }
        return users;
    }

    @Override
    public int getTotalUsersCount(User user, String searchName, String searchEmail, LocalDate searchStartDate,
            LocalDate searchEndDate) {
        String userType = user.getType();
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT COUNT(*) FROM User u WHERE u.deletedAt IS NULL";
        if (userType.equals("1")) {
            hql += " AND u.createdUserId = :userId";
        }

        if (searchName != null && !searchName.isEmpty()) {
            hql += " AND u.name LIKE :name";
        }

        if (searchEmail != null && !searchEmail.isEmpty()) {
            hql += " AND u.email LIKE :email";
        }

        if (searchStartDate != null) {
            hql += " AND u.createdAt >= :startdate ";
        }

        if (searchEndDate != null) {
            hql += " AND u.createdAt <= :enddate ";
        }

        Query<Long> query = session.createQuery(hql, Long.class);

        if (userType.equals("1")) {
            query.setParameter("userId", user.getId());
        }

        if (searchName != null && !searchName.isEmpty()) {
            query.setParameter("name", "%" + searchName + "%");
        }

        if (searchEmail != null && !searchEmail.isEmpty()) {
            query.setParameter("email", "%" + searchEmail + "%");
        }

        if (searchStartDate != null) {
            query.setParameter("startdate", searchStartDate);
        }

        if (searchEndDate != null) {
            query.setParameter("enddate", searchEndDate);
        }
        return query.getSingleResult().intValue();
    }

    @Override
    public User getUserById(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, userId);
    }

    @Override
    public void saveUser(UserForm userForm, LocalDate currentDate, Long userid) {
        User user = new User(userForm);
        int id = userid.intValue();
        user.setCreatedAt(currentDate);
        user.setUpdatedAt(currentDate);
        user.setCreatedUserId(id);
        user.setUpdatedUserId(id);
        this.sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", email)
                .uniqueResult();
    }

    @Override
    public User registerUser(UserForm userForm, LocalDate currentDate) {
        User user = new User(userForm);
        user.setCreatedAt(currentDate);
        user.setUpdatedAt(currentDate);
        this.sessionFactory.getCurrentSession().save(user);
        return user;
    }
}
