package com.hhplus.clean.interfaces.api.user;

import com.hhplus.clean.domain.user.UserCommand;
import lombok.Builder;


@Builder
public record UserRequestDTO (
        Long id,
        String userName
) {
    public UserCommand toCommand() {
        return new UserCommand(this.id, this.userName);
    }
}
