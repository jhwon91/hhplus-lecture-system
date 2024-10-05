package com.hhplus.clean.domain.dto;

import com.hhplus.clean.domain.entity.LectureEntity;
import lombok.Builder;

@Builder
public record LectureCommand(
        Long id,
        String lectureName
) {
    // Command -> Entity 변환 메소드
    public LectureEntity toEntity() {
        return new LectureEntity(this.id, this.lectureName);
    }

    // Info -> Command
    public static LectureCommand from(LectureInfo info) {
        return LectureCommand.builder()
                .id(info.id())
                .lectureName(info.lectureName())
                .build();
    }
}
