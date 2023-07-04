package scm.bulletinboard.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import scm.bulletinboard.bl.service.UserService;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.UserForm;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UserController {

    @Inject
    private ServletContext servletContext;

    @Autowired
    private UserService userService;

    @GetMapping("/users/index")
    public ModelAndView userList(Model model) {
        ModelAndView userListView = new ModelAndView("users/index");
        List<User> users = this.userService.getAllUsers();
        userListView.addObject("users", users);
        return userListView;
    }

    @GetMapping("/users/create")
    public ModelAndView createUser(Model model) {
        ModelAndView userCreatView = new ModelAndView("users/create");
        userCreatView.addObject("userForm", new UserForm());
        return userCreatView;
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult,
            @RequestParam("profileFile") MultipartFile profileFile, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "users/create";
        }
        if (!profileFile.isEmpty()) {
            try {
                String folderPath = request.getServletContext().getRealPath("/resources/profiles");
                String fileName = profileFile.getOriginalFilename();
                String filePath = folderPath + File.separator + fileName;
                File directory = new File(folderPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                Path destinationPath = Paths.get(filePath);
                profileFile.transferTo(destinationPath.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userForm.setProfile(profileFile.getOriginalFilename());
        model.addAttribute("userForm", userForm);
        return "users/confirm";
    }

    @PostMapping("/users/save")
    public ModelAndView saveUser(@ModelAttribute("userForm") UserForm userForm) {
        this.userService.saveUser(userForm);
        ModelAndView userListView = new ModelAndView("redirect:/users/index");
        return userListView;
    }

    @PostMapping("users/delete")
    public ModelAndView deleteUser(@RequestParam("id") Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            user.setDeletedAt(new Date());
            userService.deleteUser(user);
        }
        ModelAndView userListView = new ModelAndView("redirect:/users/index");
        return userListView;
    }

    @GetMapping("myprofile")
    public ModelAndView myProfile() {
        ModelAndView myProfile = new ModelAndView("users/profile");
        return myProfile;
    }

    @GetMapping("users/edit")
    public ModelAndView editUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        UserForm userForm = new UserForm();
        userForm.setId(user.getId());
        User userProfile = userService.getUserById(userForm.getId());
        ModelAndView editUserView = new ModelAndView("users/edit");
        editUserView.addObject("userProfile", userProfile);
        editUserView.addObject("userForm", userForm);
        return editUserView;
    }

    @PostMapping("users/update")
    public String updateUser(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult,
            @RequestParam("profileFile") MultipartFile profileFile, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        if (!profileFile.isEmpty()) {
            try {
                String folderPath = request.getServletContext().getRealPath("/resources/profiles");
                String fileName = profileFile.getOriginalFilename();
                String filePath = folderPath + File.separator + fileName;
                File directory = new File(folderPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                Path destinationPath = Paths.get(filePath);
                profileFile.transferTo(destinationPath.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        User user = userService.getUserById(userForm.getId());
        if (user != null) {
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            user.setType(userForm.getType());
            user.setPhone(userForm.getPhone());
            user.setDob(userForm.getDob());
            user.setAddress(userForm.getAddress());
            user.setProfile(profileFile.getOriginalFilename());
            userService.updateUser(user);
        }
        model.addAttribute("userForm", userForm);
        return "redirect:/users/index";
    }
}
