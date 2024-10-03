package com.hhplus.clean.interfaces.api.dto;

import com.hhplus.clean.domain.dto.LectureEnrollCommand;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import com.hhplus.clean.domain.entity.UserEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LectureEnrollRequestDTO(
        Long id,
        UserEntity user,
        LectureScheduleEntity lectureSchedule,
        LocalDateTime enrollTime
) {
    public LectureEnrollCommand toCommand() {
        return new LectureEnrollCommand(
                this.id,
                this.user,
                this.lectureSchedule,
                this.enrollTime
        );
    }
}
