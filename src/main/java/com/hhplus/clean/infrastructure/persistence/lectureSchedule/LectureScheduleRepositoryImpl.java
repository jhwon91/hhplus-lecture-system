package com.hhplus.clean.infrastructure.persistence.lectureSchedule;

import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import com.hhplus.clean.domain.Repository.LectureScheduleRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class LectureScheduleRepositoryImpl implements LectureScheduleRepository {
    private final JpaLectureScheduleRepository jpaLectureScheduleRepository;

    public LectureScheduleRepositoryImpl(JpaLectureScheduleRepository jpaLectureSchduleRepository) {
        this.jpaLectureScheduleRepository = jpaLectureSchduleRepository;
    }

    @Override
    public LectureScheduleEntity save(LectureScheduleEntity lectureScheduleEntity) {
        return jpaLectureScheduleRepository.save(lectureScheduleEntity);
    }

    @Override
    public Optional<LectureScheduleEntity> findById(long id) {
        return jpaLectureScheduleRepository.findById(id);
    }

    @Override
    public List<LectureScheduleEntity> findAll() {
        return jpaLectureScheduleRepository.findAll();
    }

    @Override
    public List<LectureScheduleEntity> findByRegDate(LocalDate regDate) {
        return jpaLectureScheduleRepository.findByRegDate(regDate);
    }

    @Override
    public LectureScheduleEntity findByIdForUpdate(long id) {
        return jpaLectureScheduleRepository.findByIdForUpdate(id);
    }

    @Override
    public Optional<LectureScheduleEntity> findByIdWithPessimisticLock(Long id) {
        return jpaLectureScheduleRepository.findByIdWithPessimisticLock(id);
    }
}
