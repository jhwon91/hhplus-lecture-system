package com.hhplus.clean.infrastructure.persistence.lectureEnroll;

import com.hhplus.clean.domain.lectureEnroll.LectureEnrollEntity;
import com.hhplus.clean.domain.lectureEnroll.LectureEnrollRepository;

import java.util.List;
import java.util.Optional;

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
}
