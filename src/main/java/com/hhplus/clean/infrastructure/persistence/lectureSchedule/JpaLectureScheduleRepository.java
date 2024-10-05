package com.hhplus.clean.infrastructure.persistence.lectureSchedule;

import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JpaLectureScheduleRepository extends JpaRepository<LectureScheduleEntity, Long> {
    List<LectureScheduleEntity> findByRegDate(LocalDate regDate);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ls FROM LectureScheduleEntity ls WHERE ls.id = :lecture_schedule_id")
    LectureScheduleEntity findByIdForUpdate(@Param("lecture_schedule_id") Long lecture_schedule_id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ls FROM LectureScheduleEntity ls WHERE ls.id = :lecture_schedule_id")
    Optional<LectureScheduleEntity> findByIdWithPessimisticLock(@Param("lecture_schedule_id") Long id);

}
