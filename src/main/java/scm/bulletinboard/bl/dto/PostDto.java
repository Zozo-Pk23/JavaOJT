package scm.bulletinboard.bl.dto;

import scm.bulletinboard.persistance.entity.Post;

public class PostDto {
    private Post post;
    private String userName;
    private String updatedUserName;

    public PostDto(Post post, String userName, String updatedUserName) {
        this.post = post;
        this.userName = userName;
        this.updatedUserName = updatedUserName;
    }

    public Post getPost() {
        return post;
    }

    public String getUserName() {
        return userName;
    }

    public String getUpdatedUserName() {
        return updatedUserName;
    }
}
