package com.hhplus.clean.domain.user;

import com.hhplus.clean.infrastructure.entity.UserEntity;

public class UserCommand {
    private Long id;
    private String userName;

    public UserCommand() {}

    public UserCommand(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserEntity toEntity() {
        return new UserEntity(this.id, this.userName);
    }

    public static UserCommand form(UserInfo user){
        return new UserCommand(user.getId(), user.getUserName());
    }
}
