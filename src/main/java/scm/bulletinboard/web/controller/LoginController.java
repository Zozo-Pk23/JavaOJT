package scm.bulletinboard.web.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.LoginForm;

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
        loginPage.addObject("loginform", new LoginForm());
        return loginPage;
    }

    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute("loginform") @Valid LoginForm loginForm, BindingResult bindingResult,
            @RequestParam("email") String email,
            @RequestParam("password") String password, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }
        Session hibernateSession = sessionFactory.getCurrentSession();
        String queryStr = "FROM User u WHERE u.email = :email";
        Query<User> query = hibernateSession.createQuery(queryStr, User.class);
        query.setParameter("email", email);
        User user = query.uniqueResult();

        if (user == null) {
            model.addAttribute("emailNotFound", "Email does not exists");
            return "auth/login";
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("incorrectPassword", "Incorrect password!!");
            return "auth/login";
        } else {
            session.setAttribute("user", user);
            return "redirect:/posts/index";
        }
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("user");
        return new ModelAndView("redirect:/login");
    }

}
