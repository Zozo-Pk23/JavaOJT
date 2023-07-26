package scm.bulletinboard.persistance.entity;

import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import scm.bulletinboard.web.form.PostForm;

@AllArgsConstructor
@Data
@Entity
@Table(name = "posts")
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

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

    public Post() {
        super();
    }

    public Post(PostForm postForm) {
        this.title = postForm.getTitle();
        this.description = postForm.getDescription();
        this.status = postForm.getStatus();
        this.createdUserId=postForm.getCreatedUserId();
        this.updatedUserId = postForm.getUpdatedUserId();
        this.deletedUserId=postForm.getDeletedUserId();
        this.createdAt=postForm.getCreatedAt();
        this.updatedAt=postForm.getUpdatedAt();
        this.deletedAt=postForm.getDeletedAt();
    }

}
