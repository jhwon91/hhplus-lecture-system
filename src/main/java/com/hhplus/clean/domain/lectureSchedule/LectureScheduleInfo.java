package com.hhplus.clean.domain.lectureSchedule;
import com.hhplus.clean.infrastructure.entity.LectureEntity;
import com.hhplus.clean.infrastructure.entity.LectureScheduleEntity;
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
    // LectureScheduleEntity -> Info
    public static LectureScheduleInfo from(LectureScheduleEntity entity) {
        return LectureScheduleInfo.builder()
                .id(entity.getId())
                .lecture(entity.getLectureEntity())  // LectureEntity 객체 자체를 포함
                .regDate(entity.getRegDate())
                .capacity(entity.getCapacity())
                .currentCount(entity.getCurrentCount())
                .build();
    }
}
