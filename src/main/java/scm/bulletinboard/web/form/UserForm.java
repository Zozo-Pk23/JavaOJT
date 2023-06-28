package scm.bulletinboard.web.form;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserForm {
    private Long id;
    private String name;
    private String email;
    private String password;
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
}
