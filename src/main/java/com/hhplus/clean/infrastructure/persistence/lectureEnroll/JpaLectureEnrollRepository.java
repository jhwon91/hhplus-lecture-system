package com.hhplus.clean.infrastructure.persistence.lectureEnroll;

import com.hhplus.clean.domain.lectureEnroll.LectureEnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLectureEnrollRepository extends JpaRepository<LectureEnrollEntity, Long> {
}
