package com.hhplus.clean.domain.Repository;

import com.hhplus.clean.domain.entity.LectureScheduleEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LectureScheduleRepository {
    LectureScheduleEntity save(LectureScheduleEntity lectureScheduleEntity);
    Optional<LectureScheduleEntity> findById(long id);
    List<LectureScheduleEntity> findAll();
    List<LectureScheduleEntity> findByRegDate(LocalDate regDate);
    LectureScheduleEntity findByIdForUpdate(long id);
    Optional<LectureScheduleEntity> findByIdWithPessimisticLock(Long id);
}
