package com.hhplus.clean.domain.dto;

import com.hhplus.clean.domain.entity.LectureEntity;
import lombok.Builder;

@Builder
public record LectureInfo(
        Long id,
        String lectureName
) {
    // Entity -> Info를 생성하는 정적 메소드
    public static LectureInfo from(LectureEntity entity) {
        return LectureInfo.builder()
                .id(entity.getId())
                .lectureName(entity.getLectureName())
                .build();
    }
}
