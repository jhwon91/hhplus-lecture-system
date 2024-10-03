package com.hhplus.clean.interfaces.api.dto;

import com.hhplus.clean.domain.dto.UserCommand;
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
