package com.hhplus.clean.domain.lectureSchedule;

import com.hhplus.clean.domain.user.UserRepository;
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

    public LectureScheduleEntity getLectureScheduleById(Long lectureScheduleId) {
        return lectureScheduleRepository.findById(lectureScheduleId)
                .orElseThrow(() -> new RuntimeException("특강 일정을 찾을 수 없습니다."));
    }

    public List<LectureSchedule> getAvailableLectureSchedules(LocalDate date) {
        List<LectureScheduleEntity> schedules = lectureScheduleRepository.findByRegDate(date);

        return schedules.stream()
                .filter(schedule -> schedule.getLectureEnrolls().size() < schedule.getCapacity())
                .map(LectureSchedule::from)
                .collect(Collectors.toList());
    }
}
