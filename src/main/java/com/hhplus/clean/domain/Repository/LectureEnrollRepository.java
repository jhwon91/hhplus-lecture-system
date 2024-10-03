package com.hhplus.clean.domain.Repository;

import com.hhplus.clean.domain.entity.LectureEnrollEntity;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import com.hhplus.clean.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface LectureEnrollRepository {
    LectureEnrollEntity save(LectureEnrollEntity lectureEnrollEntity);
    Optional<LectureEnrollEntity> findById(long id);
    List<LectureEnrollEntity> findAll();
    boolean existsByUserAndLectureSchedule(UserEntity user, LectureScheduleEntity lectureSchedule);
    List<LectureEnrollEntity> findByUserEntity(UserEntity user);
}
