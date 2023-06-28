package scm.bulletinboard.persistance.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;
}
