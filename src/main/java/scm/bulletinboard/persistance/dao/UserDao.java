package scm.bulletinboard.persistance.dao;

import scm.bulletinboard.bl.dto.UserDto;
import scm.bulletinboard.persistance.entity.User;
import scm.bulletinboard.web.form.UserForm;
import java.util.Date;
import java.util.List;

public interface UserDao {
    List<UserDto> getAllUsers(int pageNumber, int PageSize, User user, String searchName, String searchEmail,
            Date searchStartDate,
            Date searchEndDate);

    public void saveUser(UserForm user, Date currentDate,Long userId);

    public User getUserById(Long userId);

    public void updateUser(User user);

    public void deleteUser(User user);

    int getTotalUsersCount(User user, String searchName, String searchEmail, Date searchStartDate, Date searchEndDate);

    User findByEmail(String email);

    public User registerUser(UserForm userForm,Date currenDate);
}
