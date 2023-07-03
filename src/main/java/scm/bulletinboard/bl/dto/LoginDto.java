package scm.bulletinboard.bl.dto;

import scm.bulletinboard.persistance.entity.User;

import java.io.Serializable;
public class LoginDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private String type;
    private Long id;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LoginDto() {

    }
    public LoginDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
