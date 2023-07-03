package scm.bulletinboard.web.form;

import lombok.*;
import scm.bulletinboard.customvalidator.ImageFormat;
import scm.bulletinboard.customvalidator.MaximumUploadSize;
import scm.bulletinboard.customvalidator.NotBlankFile;
import scm.bulletinboard.persistance.entity.User;
import java.util.Date;
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
    private String confirmPassword;
    private String profile;
    private String type;
    private String phone;
    private String dob;
    private String address;
    private Integer createdUserId;
    private Integer updatedUserId;
    private Integer deletedUserId;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    @NotBlankFile
    @ImageFormat
    @MaximumUploadSize
    private MultipartFile profileFile;
    public UserForm(User user){
        this.id=user.getId();
        this.name=user.getName();
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.profile=user.getProfile();
        this.type=user.getType();
        this.phone=user.getPhone();
        this.dob=user.getDob();
        this.address=user.getAddress();
        this.createdUserId=user.getCreatedUserId(); 
        this.updatedUserId = user.getUpdatedUserId();
        this.deletedUserId = user.getDeletedUserId();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.deletedAt = user.getDeletedAt();
    }
}
