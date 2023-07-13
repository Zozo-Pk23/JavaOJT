package scm.bulletinboard.bl.dto;

import scm.bulletinboard.persistance.entity.User;

public class UserDto {
    private User user;
    private String createdUserName;
    private String updatedUserName;

    public UserDto(User user, String createdUserName, String updatedUserName) {
        this.user = user;
        this.createdUserName = createdUserName;
        this.updatedUserName = updatedUserName;
    }

    public User getUser() {
        return user;
    }

    public String getCreatedUserName() {
        return createdUserName;
    }

    public String getUpdatedUserName() {
        return updatedUserName;
    }
}
