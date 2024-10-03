package com.hhplus.clean.domain.Repository;

import com.hhplus.clean.domain.entity.LectureEntity;

import java.util.List;
import java.util.Optional;

public interface LectureRepository {
    LectureEntity save(LectureEntity lectureEntity);
    Optional<LectureEntity> findById(long id);
    List<LectureEntity> findAll();
}
