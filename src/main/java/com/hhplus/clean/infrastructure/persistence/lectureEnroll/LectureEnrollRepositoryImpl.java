package com.hhplus.clean.infrastructure.persistence.lectureEnroll;

import com.hhplus.clean.infrastructure.entity.LectureEnrollEntity;
import com.hhplus.clean.domain.lectureEnroll.LectureEnrollRepository;
import com.hhplus.clean.infrastructure.entity.LectureScheduleEntity;
import com.hhplus.clean.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LectureEnrollRepositoryImpl implements LectureEnrollRepository {
    private final JpaLectureEnrollRepository jpaLectureEnrollRepository;

    public LectureEnrollRepositoryImpl(JpaLectureEnrollRepository jpaLectureEnrollRepository) {
        this.jpaLectureEnrollRepository = jpaLectureEnrollRepository;
    }

    @Override
    public LectureEnrollEntity save(LectureEnrollEntity lectureEnrollEntity) {
        return jpaLectureEnrollRepository.save(lectureEnrollEntity);
    }

    @Override
    public Optional<LectureEnrollEntity> findById(long id) {
        return jpaLectureEnrollRepository.findById(id);
    }

    @Override
    public List<LectureEnrollEntity> findAll() {
        return jpaLectureEnrollRepository.findAll();
    }

    @Override
    public boolean existsByUserAndLectureSchedule(UserEntity user, LectureScheduleEntity lectureSchedule) {
        return jpaLectureEnrollRepository.existsByUserEntityAndLectureScheduleEntity(user, lectureSchedule);
    }

    @Override
    public List<LectureEnrollEntity> findByUserEntity(UserEntity user) {
        return jpaLectureEnrollRepository.findByUserEntity(user);
    }
}
