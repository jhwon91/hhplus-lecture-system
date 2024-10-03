package com.hhplus.clean.interfaces.api.dto;
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
