package scm.bulletinboard.bl.service;

import scm.bulletinboard.persistance.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
}
