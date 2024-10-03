package com.hhplus.clean.domain.lectureSchedule;

import com.hhplus.clean.infrastructure.entity.LectureScheduleEntity;
import com.hhplus.clean.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureScheduleService {
    private final LectureScheduleRepository lectureScheduleRepository;

    public LectureScheduleService(LectureScheduleRepository lectureScheduleRepository) {
        this.lectureScheduleRepository = lectureScheduleRepository;
    }

    public LectureSchedule getLectureScheduleById(Long lectureScheduleId) {
        LectureScheduleEntity lectureScheduleEntity = lectureScheduleRepository.findById(lectureScheduleId)
                .orElseThrow(() -> new RuntimeException("특강 일정을 찾을 수 없습니다."));

        return LectureSchedule.from(lectureScheduleEntity);
    }


    public List<LectureSchedule> getAvailableLectureSchedules(LocalDate date) {
        List<LectureScheduleEntity> schedules = lectureScheduleRepository.findByRegDate(date);

        return schedules.stream()
                .filter(schedule -> schedule.getLectureEnrolls().size() < schedule.getCapacity())
                .map(LectureSchedule::from)
                .collect(Collectors.toList());
    }

    // 정원 초과 여부 확인
    public void checkCapacity(LectureSchedule lectureSchedule) {
        if (lectureSchedule.getCurrentCount() >= lectureSchedule.getCapacity()) {
            throw new RuntimeException("특강 정원이 가득 찼습니다.");
        }
    }

    public LectureSchedule incrementCurrentCount(LectureSchedule lectureSchedule) {
        int currentCount = lectureSchedule.getCurrentCount();
        lectureSchedule.setCurrentCount(++currentCount);
        return lectureSchedule;
    }

    public void saveLectureSchedule(LectureSchedule lectureSchedule) {
        lectureScheduleRepository.save(lectureSchedule.toEntity());
    }
}
