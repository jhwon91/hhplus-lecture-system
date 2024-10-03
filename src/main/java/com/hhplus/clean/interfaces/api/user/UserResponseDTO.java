package com.hhplus.clean.interfaces.api.user;
import com.hhplus.clean.domain.user.UserInfo;

public class UserResponseDTO {
    private Long id;
    private String username;

    public UserResponseDTO() {}

    public UserResponseDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    // UserInfo-> UserResponseDTO
    public static UserResponseDTO from(UserInfo user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUserName()
        );
    }
}
