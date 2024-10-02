package com.hhplus.clean.application;

import com.hhplus.clean.domain.lectureSchedule.LectureSchedule;
import com.hhplus.clean.domain.lectureSchedule.LectureScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LectureFacade {
    private final LectureScheduleService lectureScheduleService;

    public LectureFacade(LectureScheduleService lectureScheduleService) {
        this.lectureScheduleService = lectureScheduleService;
    }

    // 날짜별로 가능한 특강 스케줄을 반환하는 메소드
    public List<LectureSchedule> getAvailableLectureSchedules(LocalDate date) {
        return lectureScheduleService.getAvailableLectureSchedules(date);
    }
}
