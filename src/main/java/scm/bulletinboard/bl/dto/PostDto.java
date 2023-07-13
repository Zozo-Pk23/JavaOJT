package scm.bulletinboard.bl.dto;

import scm.bulletinboard.persistance.entity.Post;

public class PostDto {
    private Post post;
    private String userName;

    public PostDto(Post post, String userName) {
        this.post = post;
        this.userName = userName;
    }

    public Post getPost() {
        return post;
    }

    public String getUserName() {
        return userName;
    }
}
