package com.hhplus.clean.interfaces.api.dto;

import com.hhplus.clean.domain.lectureSchedule.LectureScheduleInfo;
import com.hhplus.clean.infrastructure.entity.LectureEntity;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record LectureScheduleResponseDTO(
        Long id,
        LectureEntity lecture,
        LocalDate regDate,
        int capacity,
        int currentCount
) {
    // Info-> ResponseDTO
    public static LectureScheduleResponseDTO from(LectureScheduleInfo info) {
        return new LectureScheduleResponseDTO(
                info.id(),
                info.lecture(),
                info.regDate(),
                info.capacity(),
                info.currentCount()
        );
    }
}
