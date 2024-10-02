package com.hhplus.clean.infrastructure.persistence.lectureSchedule;

import com.hhplus.clean.domain.lectureSchedule.LectureScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLectureSchduleRepository extends JpaRepository<LectureScheduleEntity, Long> {
}
