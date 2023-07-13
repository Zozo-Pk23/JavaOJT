package scm.bulletinboard.persistance.entity;

import jakarta.persistence.*;
import lombok.*;
import scm.bulletinboard.web.form.UserForm;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "profile")
    private String profile;
    @Column(name = "type")
    private String type;
    @Column(name = "phone")
    private String phone;
    @Column(name = "dob")
    private String dob;
    @Column(name = "address")
    private String address;
    @Column(name = "created_user_id")
    private Integer createdUserId;
    @Column(name = "updated_user_id")
    private Integer updatedUserId;
    @Column(name = "deleted_user_id")
    private Integer deletedUserId;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    @Column(name = "deleted_at")
    private LocalDate deletedAt;

    public User() {
        super();
    }

    public User(UserForm userForm) {
        this.name = userForm.getName();
        this.email = userForm.getEmail();
        this.password = userForm.getPassword();
        this.profile = userForm.getProfile();
        this.type = userForm.getType();
        this.phone = userForm.getPhone();
        this.dob = userForm.getDob();
        this.address = userForm.getAddress();
        this.createdUserId = userForm.getCreatedUserId();
        this.updatedUserId = userForm.getUpdatedUserId();
        this.deletedUserId = userForm.getDeletedUserId();
        this.createdAt = userForm.getCreatedAt();
        this.updatedAt = userForm.getUpdatedAt();
        this.deletedAt = userForm.getDeletedAt();
    }

}
