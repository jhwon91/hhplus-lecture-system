package com.hhplus.clean.application;

import com.hhplus.clean.domain.service.LectureEnrollService;
import com.hhplus.clean.domain.dto.LectureScheduleCommand;
import com.hhplus.clean.domain.dto.LectureScheduleInfo;
import com.hhplus.clean.domain.service.LectureScheduleService;
import com.hhplus.clean.domain.dto.UserCommand;
import com.hhplus.clean.domain.service.UserService;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LectureFacade {
    private final UserService userService;
    private final LectureScheduleService lectureScheduleService;
    private final LectureEnrollService lectureEnrollService;

    public LectureFacade(UserService userService, LectureScheduleService lectureScheduleService, LectureEnrollService lectureEnrollService) {
        this.userService = userService;
        this.lectureScheduleService = lectureScheduleService;
        this.lectureEnrollService = lectureEnrollService;
    }

    // 날짜별로 가능한 특강 스케줄을 반환하는 메소드
    public List<LectureScheduleInfo> getAvailableLectureSchedules(LocalDate date) {
        return lectureScheduleService.getAvailableLectureSchedules(date);
    }

    @Transactional
    public void enrollLecture(Long lectureId, UserCommand userCommand) {

        // 사용자 확인
        UserCommand user = UserCommand.from(userService.getUser(userCommand.id()));

        // 특강 스케줄 확인
        LectureScheduleCommand lectureSchedule = LectureScheduleCommand.from(lectureScheduleService.getLectureScheduleById(lectureId)) ;

        lectureEnrollService.checkEnroll(user, lectureSchedule);
        lectureScheduleService.checkCapacity(lectureSchedule);

        // 특강 신청 처리
        lectureEnrollService.saveEnroll(user, lectureSchedule);
        lectureScheduleService.incrementCurrentCount(lectureSchedule);
        lectureScheduleService.saveLectureSchedule(lectureSchedule);
    }
}
