package scm.bulletinboard.persistance.dao;

import scm.bulletinboard.persistance.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
}
