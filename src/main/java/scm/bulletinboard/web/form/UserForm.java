package scm.bulletinboard.web.form;

import lombok.*;
import scm.bulletinboard.persistance.entity.User;

import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserForm {
    private Long id;
    @NotBlank(message = "Name can not be blank.")
    private String name;
    @NotBlank(message = "Email can not be blank.")
    @Email(message = "Email format is invaid.")
    private String email;
    @NotBlank(message = "Password can not be blank.")
    private String password;
    @NotBlank(message = "Confirm Password is required")
    private String confirmPassword;
    private String profile;
    private String type;
    private String phone;
    private String dob;
    private String address;
    private Integer createdUserId;
    private Integer updatedUserId;
    private Integer deletedUserId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;
    private MultipartFile profileFile;

    public UserForm(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.profile = user.getProfile();
        this.type = user.getType();
        this.phone = user.getPhone();
        this.dob = user.getDob();
        this.address = user.getAddress();
        this.createdUserId = user.getCreatedUserId();
        this.updatedUserId = user.getUpdatedUserId();
        this.deletedUserId = user.getDeletedUserId();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.deletedAt = user.getDeletedAt();
    }
}
