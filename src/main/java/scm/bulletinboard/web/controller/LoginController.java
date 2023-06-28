package scm.bulletinboard.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import scm.bulletinboard.persistance.entity.User;

@Controller
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView showLoginPage(){
        ModelAndView loginPage = new ModelAndView("auth/login");
        loginPage.addObject("user",new User());
        return loginPage;
    }

    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ModelAndView authenticate(){
        ModelAndView postList = new ModelAndView("redirect:/posts/index");
        return postList;
    }
}
