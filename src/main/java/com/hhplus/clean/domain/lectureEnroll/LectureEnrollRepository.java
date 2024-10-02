package com.hhplus.clean.domain.lectureEnroll;

import java.util.List;
import java.util.Optional;

public interface LectureEnrollRepository {
    LectureEnrollEntity save(LectureEnrollEntity lectureEnrollEntity);
    Optional<LectureEnrollEntity> findById(long id);
    List<LectureEnrollEntity> findAll();
}
