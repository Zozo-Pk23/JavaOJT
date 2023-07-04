package scm.bulletinboard.web.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import scm.bulletinboard.persistance.entity.User;

@Transactional
@Controller
public class LoginController {

    private final SessionFactory sessionFactory;

    @Autowired
    public LoginController(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage() {
        ModelAndView loginPage = new ModelAndView("auth/login");
        loginPage.addObject("user", new User());
        return loginPage;
    }

    @PostMapping("/authenticate")
    public ModelAndView authenticate(@RequestParam("email") String email,
                                     @RequestParam("password") String password,HttpSession session) {
        Session hibernateSession  = sessionFactory.getCurrentSession();
        String queryStr = "FROM User u WHERE u.email = :email AND u.password = :password";
        Query<User> query = hibernateSession.createQuery(queryStr, User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        User user = query.uniqueResult();
        if (user != null) {
            session.setAttribute("user", user);
            return new ModelAndView("redirect:/posts/index");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }
    
}
