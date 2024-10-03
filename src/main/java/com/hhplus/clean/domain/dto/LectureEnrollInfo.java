package com.hhplus.clean.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhplus.clean.domain.entity.LectureEnrollEntity;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import com.hhplus.clean.domain.entity.UserEntity;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record LectureEnrollInfo(
        Long id,
        UserEntity user,
        LectureScheduleEntity lectureSchedule,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS", shape = JsonFormat.Shape.STRING)
        LocalDateTime enrollTime
) {
    // Entity -> Info
    public static LectureEnrollInfo from(LectureEnrollEntity entity) {
        return LectureEnrollInfo.builder()
                .id(entity.getId())
                .user(entity.getUserEntity())
                .lectureSchedule(entity.getLectureScheduleEntity())
                .enrollTime(entity.getEnrollTime())
                .build();
    }
}
