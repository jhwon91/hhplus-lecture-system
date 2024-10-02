package com.hhplus.clean.interfaces.api.user;

import com.hhplus.clean.domain.user.UserEntity;

public class UserResponseDTO {
    private Long id;
    private String username;

    // 정적 팩토리 메서드로 UserEntity를 UserResponseDTO로 변환
    public static UserResponseDTO from(UserEntity userEntity) {
        UserResponseDTO response = new UserResponseDTO();
        response.setId(userEntity.getId());
        response.setUsername(userEntity.getUsername());
        return response;

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
}
