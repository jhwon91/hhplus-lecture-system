package com.hhplus.clean.interfaces.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhplus.clean.domain.dto.LectureScheduleInfo;
import com.hhplus.clean.domain.entity.LectureEntity;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
public record LectureScheduleResponseDTO(
        Long id,
        LectureEntity lecture,

        @JsonFormat(pattern = "yyyy-MM-dd")
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
