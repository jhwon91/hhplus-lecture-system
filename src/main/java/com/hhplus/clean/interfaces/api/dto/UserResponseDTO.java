package com.hhplus.clean.interfaces.api.dto;
import com.hhplus.clean.domain.dto.UserInfo;
import lombok.Builder;

@Builder
public record UserResponseDTO(
        Long id,
        String userName
) {

    // UserInfo-> UserResponseDTO
    public static UserResponseDTO from(UserInfo user) {
        return new UserResponseDTO(
                user.id(),
                user.userName()
        );
    }
}