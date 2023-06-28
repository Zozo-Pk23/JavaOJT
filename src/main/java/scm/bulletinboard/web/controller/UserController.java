package scm.bulletinboard.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import scm.bulletinboard.bl.service.UserService;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.UserForm;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users/index")
    public ModelAndView userList(Model model) {
        ModelAndView userListView = new ModelAndView("users/index");
        List<User> users=this.userService.getAllUsers();
        userListView.addObject("users",users);
        return userListView;
    }

    @GetMapping("/users/create")
    public ModelAndView createUser(Model model){
        ModelAndView userCreatView=new ModelAndView("users/create");
        userCreatView.addObject("userForm", new UserForm());
        return userCreatView;
    }
}
