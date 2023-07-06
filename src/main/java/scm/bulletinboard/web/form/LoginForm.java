package scm.bulletinboard.web.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import scm.bulletinboard.persistance.entity.User;

@Data
@NoArgsConstructor
public class LoginForm {
    @NotBlank(message = "Email can not be blank.")
    @Email(message = "Email format is invaid.")
    private String email;
    @NotBlank(message = "Password can not be blank.")
    private String password;

    public LoginForm(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
