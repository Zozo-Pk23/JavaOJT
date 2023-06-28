package scm.bulletinboard.bl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import scm.bulletinboard.bl.service.UserService;
import scm.bulletinboard.persistance.dao.UserDao;
import scm.bulletinboard.persistance.entity.User;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
