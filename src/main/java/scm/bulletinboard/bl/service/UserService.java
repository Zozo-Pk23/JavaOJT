package scm.bulletinboard.bl.service;

import scm.bulletinboard.bl.dto.UserDto;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.UserForm;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserService {
        public List<UserDto> getAllUsers(int pageNumber, int pageSize, User user, String searchName, String searchEmail,
                        Date searchStartDate, Date searchEndDate);

        public void saveUser(UserForm userForm, Long userId);

        public User getUserById(Long userId);

        public void updateUser(User user);

        public void deleteUser(User user);

        int getTotalUsersCount(User user, String searchName, String searchEmail,
                        Date searchStartDate, Date searchEndDate);

        boolean checkIfEmailExists(String email);

        public void sendPasswordResetEmail(String recipientEmail, String resetLink, String userName);

        public User getUserByEmail(String email);

        public User registerUser(UserForm userForm);
}
