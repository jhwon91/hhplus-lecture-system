package com.hhplus.clean.interfaces.api.user;

import com.hhplus.clean.domain.user.UserCommand;

public class UserRequestDTO {
    private Long id;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserCommand toCommand() {
        return new UserCommand(this.id, this.username);
    }
}
