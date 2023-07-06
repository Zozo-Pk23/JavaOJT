package scm.bulletinboard.web.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import scm.bulletinboard.persistance.entity.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Data
@NoArgsConstructor
public class PostForm {
    private Long id;

    @NotEmpty(message = "Title must not be Empty.")
    @Size(max = 255, message = "255 character is the maximum allowed.")
    private String title;

    @NotEmpty(message = "Description must not be Empty.")
    private String description;

    private String status;

    private Integer createdUserId;

    private Integer updatedUserId;

    private Integer deletedUserId;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    public PostForm(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.status = post.getStatus();
        this.createdUserId = post.getCreatedUserId();
        this.updatedUserId = post.getUpdatedUserId();
        this.deletedUserId = post.getDeletedUserId();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.deletedAt = post.getDeletedAt();
    }
}
