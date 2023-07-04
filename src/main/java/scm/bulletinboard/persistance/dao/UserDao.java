package scm.bulletinboard.persistance.dao;

import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.UserForm;

import java.util.Date;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    public void saveUser(UserForm user, Date currentDate);
    public User getUserById(Long userId);
    public void updateUser(User user);
    public void deleteUser(User user);
}
