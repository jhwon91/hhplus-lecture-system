package com.hhplus.clean.interfaces.api.dto;

import com.hhplus.clean.domain.lectureSchedule.LectureScheduleCommand;
import com.hhplus.clean.infrastructure.entity.LectureEntity;

import java.time.LocalDate;

public record LectureScheduleRequestDTO(
        Long id,
        LectureEntity lecture,
        LocalDate regDate,
        int capacity,
        int currentCount
) {
    public LectureScheduleCommand toCommand() {
        return new LectureScheduleCommand(
                this.id,
                this.lecture,
                this.regDate,
                this.capacity,
                this.currentCount
        );
    }
}
