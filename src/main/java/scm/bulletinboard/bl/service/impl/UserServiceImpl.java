package scm.bulletinboard.bl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import scm.bulletinboard.bl.dto.UserDto;
import scm.bulletinboard.bl.service.UserService;
import scm.bulletinboard.persistance.dao.UserDao;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.UserForm;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final JavaMailSender mailSender;

    @Autowired
    public UserServiceImpl(UserDao userDao, JavaMailSender mailSender) {
        this.userDao = userDao;
        this.mailSender = mailSender;
    }

    public List<UserDto> getAllUsers(int pageNumber, int pageSize, User user, String searchName, String searchEmail,
    		Date searchStartDate, Date searchEndDate) {
        return userDao.getAllUsers(pageNumber, pageSize, user, searchName, searchEmail, searchStartDate, searchEndDate);
    }

    public void saveUser(UserForm userForm, Long userId) {
        this.userDao.saveUser(userForm, new Date(), userId);
    }

    public User getUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    public void deleteUser(User user) {
        this.userDao.deleteUser(user);
    }

    public int getTotalUsersCount(User user, String searchName, String searchEmail, Date searchStartDate,
            Date searchEndDate) {
        return this.userDao.getTotalUsersCount(user, searchName, searchEmail, searchStartDate, searchEndDate);
    }

    public boolean checkIfEmailExists(String email) {
        return userDao.findByEmail(email) != null;
    }

    public void sendPasswordResetEmail(String recipientEmail, String resetLink, String userName) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(recipientEmail);
            helper.setSubject("Welcome to Bulletin Board - " + userName);

            String emailContent = "Dear " + userName + ",<br><br>"
                    + "Thank you for joining Bulletin Board.<br>"
                    + "Please click the link below to reset your password:<br>"
                    + "<a href=\"" + resetLink + "\">Reset Password</a><br><br>"
                    + "If you did not request a password reset, please ignore this email.<br>"
                    + "Thanks for joining and have a great day!";

            helper.setText(emailContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
        }
    }

    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User registerUser(UserForm userForm) {
        return this.userDao.registerUser(userForm, new Date());
    }
}
