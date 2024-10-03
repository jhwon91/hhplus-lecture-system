package com.hhplus.clean.infrastructure.persistence.lecture;

import com.hhplus.clean.infrastructure.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLectureRepository extends JpaRepository<LectureEntity, Long> {
}
