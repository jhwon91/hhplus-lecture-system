package com.hhplus.clean.infrastructure.persistence.lecture;

import com.hhplus.clean.domain.entity.LectureEntity;
import com.hhplus.clean.domain.Repository.LectureRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LectureRepositoryImpl implements LectureRepository {
    private final JpaLectureRepository jpaLectureRepository;

    public LectureRepositoryImpl(JpaLectureRepository jpaLectureRepository) {
        this.jpaLectureRepository = jpaLectureRepository;
    }

    @Override
    public LectureEntity save(LectureEntity lectureEntity) {
        return jpaLectureRepository.save(lectureEntity);
    }

    @Override
    public Optional<LectureEntity> findById(long id) {
        return jpaLectureRepository.findById(id);
    }

    @Override
    public List<LectureEntity> findAll() {
        return jpaLectureRepository.findAll();
    }
}
