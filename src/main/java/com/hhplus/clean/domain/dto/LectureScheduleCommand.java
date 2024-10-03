package com.hhplus.clean.domain.dto;

import com.hhplus.clean.domain.entity.LectureEntity;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record LectureScheduleCommand(
        Long id,
        LectureEntity lecture,
        LocalDate regDate,
        int capacity,
        int currentCount
) {
    // Command -> Entity 변환 메소드
    public LectureScheduleEntity toEntity() {
        return new LectureScheduleEntity(
                this.id,
                this.lecture,
                this.regDate,
                this.capacity,
                this.currentCount
        );
    }

    // Info -> Command
    public static LectureScheduleCommand from(LectureScheduleInfo info) {
        return LectureScheduleCommand.builder()
                .id(info.id())
                .lecture(info.lecture())
                .regDate(info.regDate())
                .capacity(info.capacity())
                .currentCount(info.currentCount())
                .build();
    }
}
