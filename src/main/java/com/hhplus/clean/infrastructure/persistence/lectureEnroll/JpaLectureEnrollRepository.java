package com.hhplus.clean.infrastructure.persistence.lectureEnroll;

import com.hhplus.clean.domain.entity.LectureEnrollEntity;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import com.hhplus.clean.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaLectureEnrollRepository extends JpaRepository<LectureEnrollEntity, Long> {
    // 사용자와 특강 스케줄을 기반으로 이미 신청했는지 확인
    boolean existsByUserEntityAndLectureScheduleEntity(UserEntity user, LectureScheduleEntity lectureSchedule);
    List<LectureEnrollEntity> findByUserEntity(UserEntity user);
}
