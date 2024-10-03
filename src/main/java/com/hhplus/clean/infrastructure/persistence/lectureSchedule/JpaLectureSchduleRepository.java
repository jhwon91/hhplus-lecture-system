package com.hhplus.clean.infrastructure.persistence.lectureSchedule;

import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JpaLectureSchduleRepository extends JpaRepository<LectureScheduleEntity, Long> {
    List<LectureScheduleEntity> findByRegDate(LocalDate regDate);
}
