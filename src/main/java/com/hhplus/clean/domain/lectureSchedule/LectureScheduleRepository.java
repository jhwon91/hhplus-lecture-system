package com.hhplus.clean.domain.lectureSchedule;

import java.util.List;
import java.util.Optional;

public interface LectureScheduleRepository {
    LectureScheduleEntity save(LectureScheduleEntity lectureScheduleEntity);
    Optional<LectureScheduleEntity> findById(long id);
    List<LectureScheduleEntity> findAll();
}
