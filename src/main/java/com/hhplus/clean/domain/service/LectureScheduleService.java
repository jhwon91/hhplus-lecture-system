package com.hhplus.clean.domain.service;

import com.hhplus.clean.domain.Repository.LectureScheduleRepository;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import com.hhplus.clean.domain.dto.LectureScheduleCommand;
import com.hhplus.clean.domain.dto.LectureScheduleInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureScheduleService {
    private final LectureScheduleRepository lectureScheduleRepository;

    public LectureScheduleService(LectureScheduleRepository lectureScheduleRepository) {
        this.lectureScheduleRepository = lectureScheduleRepository;
    }

    public LectureScheduleInfo getLectureScheduleById(Long lectureScheduleId) {
        LectureScheduleEntity lectureScheduleEntity = lectureScheduleRepository.findById(lectureScheduleId)
                .orElseThrow(() -> new RuntimeException("특강 일정을 찾을 수 없습니다."));

        return LectureScheduleInfo.from(lectureScheduleEntity);
    }

    public List<LectureScheduleInfo> getAvailableLectureSchedules(LocalDate date) {
        List<LectureScheduleEntity> schedules = lectureScheduleRepository.findByRegDate(date);

        return schedules.stream()
                .filter(schedule -> schedule.getLectureEnrolls().size() < schedule.getCapacity())
                .map(LectureScheduleInfo::from)
                .collect(Collectors.toList());
    }

    // 정원 초과 여부 확인
    public void checkCapacity(LectureScheduleCommand lectureSchedule) {
        if (lectureSchedule.currentCount() >= lectureSchedule.capacity()) {
            throw new RuntimeException("특강 정원이 가득 찼습니다.");
        }
    }

    @Transactional
    public LectureScheduleInfo incrementCurrentCount(LectureScheduleCommand lectureSchedule) {
        LectureScheduleEntity schedule = lectureScheduleRepository.findByIdForUpdate(lectureSchedule.id());
        LectureScheduleEntity scheduleIncrement = new LectureScheduleEntity(
                schedule.getId(),
                schedule.getLectureEntity(),
                schedule.getRegDate(),
                schedule.getCapacity(),
                schedule.getCurrentCount() + 1
        );

        return LectureScheduleInfo.from(scheduleIncrement) ;
    }

    @Transactional
    public LectureScheduleInfo saveLectureSchedule(LectureScheduleCommand lectureSchedule) {
        return LectureScheduleInfo.from(lectureScheduleRepository.save(lectureSchedule.toEntity()));
    }

    @Transactional
    public LectureScheduleInfo findByIdForUpdate(LectureScheduleCommand lectureSchedule) {
        LectureScheduleEntity lectureScheduleEntity = lectureScheduleRepository.findByIdForUpdate(lectureSchedule.id());;

        return LectureScheduleInfo.from(lectureScheduleEntity);
    }
}
