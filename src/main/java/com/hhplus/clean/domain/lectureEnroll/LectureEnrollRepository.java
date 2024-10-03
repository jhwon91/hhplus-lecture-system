package com.hhplus.clean.domain.lectureEnroll;

import com.hhplus.clean.infrastructure.entity.LectureEnrollEntity;
import com.hhplus.clean.infrastructure.entity.LectureScheduleEntity;
import com.hhplus.clean.infrastructure.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface LectureEnrollRepository {
    LectureEnrollEntity save(LectureEnrollEntity lectureEnrollEntity);
    Optional<LectureEnrollEntity> findById(long id);
    List<LectureEnrollEntity> findAll();
    boolean existsByUserAndLectureSchedule(UserEntity user, LectureScheduleEntity lectureSchedule);
    List<LectureEnrollEntity> findByUserEntity(UserEntity user);
}
