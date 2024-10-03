package com.hhplus.clean.domain.dto;

import com.hhplus.clean.domain.entity.LectureEnrollEntity;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import com.hhplus.clean.domain.entity.UserEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LectureEnrollCommand(
        Long id,
        UserEntity user,
        LectureScheduleEntity lectureSchedule,
        LocalDateTime enrollTime
) {
    // Command -> Entity 변환 메소드
    public LectureEnrollEntity toEntity() {
        return new LectureEnrollEntity(
                this.id,
                this.user,
                this.lectureSchedule,
                this.enrollTime
        );
    }

    // Info -> Command
    public static LectureEnrollCommand from(LectureEnrollInfo info) {
        return LectureEnrollCommand.builder()
                .id(info.id())
                .user(info.user())
                .lectureSchedule(info.lectureSchedule())
                .enrollTime(info.enrollTime())
                .build();
    }
}
