package com.hhplus.clean.domain.user;

import com.hhplus.clean.infrastructure.entity.UserEntity;
import lombok.Builder;

@Builder
public record UserCommand (
        Long id,
        String userName
) {
    // Command -> Entity 변환 메소드
    public UserEntity toEntity() {
        return new UserEntity(this.id, this.userName);
    }

    // Info -> Command
    public static UserCommand from(UserInfo info) {
        return UserCommand.builder()
                .id(info.id())
                .userName(info.userName())
                .build();
    }
}
