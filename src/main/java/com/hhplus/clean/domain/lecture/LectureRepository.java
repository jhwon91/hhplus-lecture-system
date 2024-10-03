package com.hhplus.clean.domain.lecture;

import com.hhplus.clean.infrastructure.entity.LectureEntity;

import java.util.List;
import java.util.Optional;

public interface LectureRepository {
    LectureEntity save(LectureEntity lectureEntity);
    Optional<LectureEntity> findById(long id);
    List<LectureEntity> findAll();
}
