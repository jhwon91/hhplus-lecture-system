package com.hhplus.clean.interfaces.api.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhplus.clean.domain.dto.LectureEnrollInfo;
import com.hhplus.clean.domain.dto.LectureScheduleInfo;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import com.hhplus.clean.domain.entity.UserEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LectureEnrollResponseDTO(
        Long id,
        UserEntity user,
        LectureScheduleEntity lectureSchedule,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS", shape = JsonFormat.Shape.STRING)
        LocalDateTime enrollTime
) {
    // Info-> ResponseDTO
    public static LectureEnrollResponseDTO from(LectureEnrollInfo info) {
        return new LectureEnrollResponseDTO(
                info.id(),
                info.user(),
                info.lectureSchedule(),
                info.enrollTime()
        );
    }
}
