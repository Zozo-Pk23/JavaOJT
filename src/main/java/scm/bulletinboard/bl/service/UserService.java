package scm.bulletinboard.bl.service;

import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.UserForm;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public void saveUser(UserForm userForm);

    public User getUserById(Long userId);

    public void updateUser(User user);

    public void deleteUser(User user);
}
