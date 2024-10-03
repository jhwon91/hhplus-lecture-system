package com.hhplus.clean.interfaces.api.user;
import com.hhplus.clean.domain.user.UserInfo;
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