package scm.bulletinboard.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import scm.bulletinboard.bl.dto.UserDto;
import scm.bulletinboard.bl.service.UserService;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private int calculateTotalPages(int pageSize, int totalUsers) {
        return (int) Math.ceil((double) totalUsers / pageSize);
    }

    @GetMapping("/users/index")
    public ModelAndView userList(@RequestParam(defaultValue = "1") int pageNumber,
                                 @RequestParam(required = false) String searchName,
                                 @RequestParam(required = false) String searchEmail,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date searchStartDate,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date searchEndDate,
                                 HttpSession session) {

        User user = (User) session.getAttribute("user");
        int pageSize = 5;
        ModelAndView userListView = new ModelAndView("users/index");
        List<UserDto> users = this.userService.getAllUsers(pageNumber, pageSize, user, searchName, searchEmail,
                searchStartDate, searchEndDate);
        int totalUsers = this.userService.getTotalUsersCount(user, searchName, searchEmail, searchStartDate, searchEndDate);
        userListView.addObject("users", users);
        userListView.addObject("currentPage", pageNumber);
        userListView.addObject("totalPages", calculateTotalPages(pageSize, totalUsers));
        userListView.addObject("totalUsers", totalUsers);
        userListView.addObject("searchName", searchName);
        userListView.addObject("searchEmail", searchEmail);
        userListView.addObject("searchStartDate", searchStartDate);
        userListView.addObject("searchEndDate", searchEndDate);
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
        } else if (profileFile.isEmpty()) {
            bindingResult.rejectValue("profile", "error.profile",
                    "Profile can not be blank.");
            return "users/create";
        } else if (!profileFile.getOriginalFilename().matches(".*\\.(jpg|jpeg|png|gif)$")) {
            bindingResult.rejectValue("profile", "error.profile",
                    "Profile must be an image.");
            return "users/create";
        } else if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword",
                    "Password and Confirm Password must match");
            return "users/create";
        } else if (this.userService.checkIfEmailExists(userForm.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Email is already existed");
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
    public ModelAndView saveUser(@ModelAttribute("userForm") UserForm userForm, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String simeplePassword = userForm.getPassword();
        String hashPassword = passwordEncoder.encode(simeplePassword);
        userForm.setPassword(hashPassword);
        Long userId = user.getId();
        this.userService.saveUser(userForm, userId);
        String message = "User successfully created";
        ModelAndView userListView = new ModelAndView("redirect:/users/index?created=" + message);
        return userListView;
    }

    @PostMapping("users/delete")
    public ModelAndView deleteUser(@RequestParam("id") Long userId, HttpSession session) {
        User auth = (User) session.getAttribute("user");
        int authId = auth.getId().intValue();
        User user = userService.getUserById(userId);
        if (user != null) {
            user.setDeletedAt(new Date());
            user.setDeletedUserId(authId);
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
        User userProfile = userService.getUserById(user.getId());
        UserForm userForm = new UserForm();
        userForm.setId(userProfile.getId());
        userForm.setName(userProfile.getName());
        userForm.setEmail(userProfile.getEmail());
        userForm.setPassword(userProfile.getPassword());
        userForm.setConfirmPassword(userProfile.getPassword());
        userForm.setProfile(userProfile.getProfile());
        userForm.setType(userProfile.getType());
        userForm.setPhone(userProfile.getPhone());
        userForm.setDob(userProfile.getDob());
        userForm.setAddress(userProfile.getAddress());
        ModelAndView editUserView = new ModelAndView("users/edit");
        editUserView.addObject("userForm", userForm);
        return editUserView;
    }

    @PostMapping("users/update")
    public String updateUser(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult,
            @RequestParam("profileFile") MultipartFile profileFile, HttpServletRequest request, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        if (!profileFile.isEmpty() && !profileFile.getOriginalFilename().matches(".*\\.(jpg|jpeg|png|gif)$")) {
            bindingResult.rejectValue("profile", "error.profile",
                    "Profile must be an image.");
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
                userForm.setProfile(profileFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        User user = userService.getUserById(userForm.getId());
        User auth = (User) session.getAttribute("user");
        int userId = auth.getId().intValue();
        if (user != null) {
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            user.setType(userForm.getType());
            user.setPhone(userForm.getPhone());
            user.setDob(userForm.getDob());
            user.setAddress(userForm.getAddress());
            user.setProfile(userForm.getProfile());
            user.setUpdatedUserId(userId);
            user.setUpdatedAt(new Date());
            userService.updateUser(user);
        }
        String message = "User profile successfully updated";
        return "redirect:/users/index?created=" + message;
    }
}
