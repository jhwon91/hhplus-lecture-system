package com.hhplus.clean.infrastructure.persistence.lectureSchedule;

import com.hhplus.clean.domain.lectureSchedule.LectureScheduleEntity;
import com.hhplus.clean.domain.lectureSchedule.LectureScheduleRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class LectureScheduleRepositoryImpl implements LectureScheduleRepository {
    private final JpaLectureSchduleRepository jpaLectureSchduleRepository;

    public LectureScheduleRepositoryImpl(JpaLectureSchduleRepository jpaLectureSchduleRepository) {
        this.jpaLectureSchduleRepository = jpaLectureSchduleRepository;
    }

    @Override
    public LectureScheduleEntity save(LectureScheduleEntity lectureScheduleEntity) {
        return jpaLectureSchduleRepository.save(lectureScheduleEntity);
    }

    @Override
    public Optional<LectureScheduleEntity> findById(long id) {
        return jpaLectureSchduleRepository.findById(id);
    }

    @Override
    public List<LectureScheduleEntity> findAll() {
        return jpaLectureSchduleRepository.findAll();
    }

    @Override
    public List<LectureScheduleEntity> findByRegDate(LocalDate regDate) {
        return jpaLectureSchduleRepository.findByRegDate(regDate);
    }
}
