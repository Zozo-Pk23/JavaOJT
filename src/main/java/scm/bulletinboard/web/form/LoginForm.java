package scm.bulletinboard.web.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import scm.bulletinboard.persistance.entity.User;

public class LoginForm {
    @Email
    @NotEmpty
    private String email;
    private String password;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LoginForm() {
    }
    public LoginForm(User user) {
        super();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
