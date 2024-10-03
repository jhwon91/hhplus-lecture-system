package com.hhplus.clean.domain.dto;
import com.hhplus.clean.domain.entity.LectureEntity;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record LectureScheduleInfo(
        Long id,
        LectureEntity lecture,
        LocalDate regDate,
        int capacity,
        int currentCount
) {
    // Entity -> Info
    public static LectureScheduleInfo from(LectureScheduleEntity entity) {
        return LectureScheduleInfo.builder()
                .id(entity.getId())
                .lecture(entity.getLectureEntity())
                .regDate(entity.getRegDate())
                .capacity(entity.getCapacity())
                .currentCount(entity.getCurrentCount())
                .build();
    }
}
