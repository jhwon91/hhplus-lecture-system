package com.hhplus.clean.domain.user;

import com.hhplus.clean.infrastructure.entity.UserEntity;
import lombok.Builder;

@Builder
public record UserInfo (
        Long id,
        String userName
) {
    // Entity -> Info를 생성하는 정적 메소드
    public static UserInfo from(UserEntity entity) {
        return UserInfo.builder()
                .id(entity.getId())
                .userName(entity.getUsername())
                .build();
    }
}
