package scm.bulletinboard.bl.service;

import scm.bulletinboard.persistance.entity.User;

public interface AuthService {
    public User doGetLoginResult(User loginForm);
}
