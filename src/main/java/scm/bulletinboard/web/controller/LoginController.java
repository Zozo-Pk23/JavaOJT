package scm.bulletinboard.web.controller;

import java.util.UUID;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import scm.bulletinboard.bl.service.UserService;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.LoginForm;
import scm.bulletinboard.web.form.ResetPasswordForm;
import scm.bulletinboard.web.form.UserForm;

@Transactional
@Controller
public class LoginController {

    private final SessionFactory sessionFactory;
    private final UserService userService;

    @Autowired
    public LoginController(SessionFactory sessionFactory, UserService userService) {
        this.sessionFactory = sessionFactory;
        this.userService = userService;
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

    @GetMapping("users/resetPassword")
    public ModelAndView resetPasswordForm() {
        ModelAndView resetPasswordForm = new ModelAndView("auth/passwordReset");
        resetPasswordForm.addObject("passwordForm", new ResetPasswordForm());
        return resetPasswordForm;
    }

    @PostMapping("users/ChangePassword")
    public String changePassword(@ModelAttribute("passwordForm") @Valid ResetPasswordForm resetPasswordForm,
            BindingResult bindingResult, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String oldPassword = user.getPassword();
        if (bindingResult.hasErrors()) {
            return "auth/passwordReset";
        } else if (!resetPasswordForm.getOldPassword().equals(oldPassword)) {
            bindingResult.rejectValue("oldPassword", "error.oldPassword",
                    "Current Password is wrong");
            return "auth/passwordReset";
        } else if (!resetPasswordForm.getNewPassword().equals(resetPasswordForm.getNewConfirmPassword())) {
            bindingResult.rejectValue("newConfirmPassword", "error.newConfirmPassword",
                    "New Password and New confirmation password does not match.");
            return "auth/passwordReset";
        }
        User userData = userService.getUserById(user.getId());
        userData.setPassword(resetPasswordForm.getNewPassword());
        String message = "Password is successfully updated.";
        return "redirect:/users/index?created=" + message;
    }

    @GetMapping("forgotPassword")
    public ModelAndView forgotPasswordForm() {
        ModelAndView forgotPasswordForm = new ModelAndView("auth/forgotPassword");
        return forgotPasswordForm;
    }

    @PostMapping("reset")
    public String resetPassword(@RequestParam("email") String email, Model model, HttpServletRequest request) {
        Session hibernateSession = sessionFactory.getCurrentSession();
        String queryStr = "FROM User u WHERE u.email = :email";
        Query<User> query = hibernateSession.createQuery(queryStr, User.class);
        query.setParameter("email", email);
        User user = query.uniqueResult();
        if (user == null) {
            model.addAttribute("emailNotFound", "Email does not exists");
            return "auth/forgotPassword";
        }
        User sendUser = this.userService.getUserByEmail(email);
        String userName = sendUser.getName();
        String token = UUID.randomUUID().toString();
        String resetLink = "http://localhost:8080/SCMBulletin_war/resetPasswordForm?token=" + token + "&email=" + email;
        this.userService.sendPasswordResetEmail(email, resetLink, userName);
        model.addAttribute("success", "Email sent with password reset instructions.");
        return "auth/forgotPassword";
    }

    @RequestMapping(value = "/resetPasswordForm", method = RequestMethod.GET)
    public ModelAndView showResetPasswordForm(@RequestParam("token") String token,
            @RequestParam("email") String email) {
        ModelAndView passwordForm = new ModelAndView("auth/passwordConfirmation");
        passwordForm.addObject("email", email);
        passwordForm.addObject("passwordForm", new ResetPasswordForm());
        return passwordForm;
    }

    @RequestMapping(value = "/reset/password", method = RequestMethod.POST)
    public String passwordReset(@ModelAttribute("passwordForm") @Valid ResetPasswordForm resetPasswordForm,
            BindingResult bindingResult, Model model, @RequestParam("email") String email) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("email", email);
            return "auth/passwordConfirmation";
        }
        if (!resetPasswordForm.getNewPassword().equals(resetPasswordForm.getNewConfirmPassword())) {
            model.addAttribute("email", email);
            model.addAttribute("doesNotMatch", "Password and Password Confirmation does not match.");
            return "auth/passwordConfirmation";
        }
        User user = this.userService.getUserByEmail(email);
        user.setPassword(resetPasswordForm.getNewPassword());
        userService.updateUser(user);
        String message = "Password has been reset";
        return "redirect:/login?created=" + message;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView register = new ModelAndView("auth/userRegister");
        register.addObject("userForm", new UserForm());
        return register;
    }

    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public String registered(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult,
            HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "auth/userRegister";
        }
        if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword",
                    "Password and password confirmation is not match.");
        } else if (this.userService.checkIfEmailExists(userForm.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Email is already existed");
            return "auth/userRegister";
        }
        userForm.setType("1");
        User saveduser=this.userService.registerUser(userForm);
        session.setAttribute("user", saveduser);
        String message = "User sign up successful.";
        return "redirect:/posts/index?created=" + message;
    }

}
