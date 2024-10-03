package com.hhplus.clean.domain.dto;

import com.hhplus.clean.domain.entity.UserEntity;
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
