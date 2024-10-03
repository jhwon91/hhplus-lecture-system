package com.hhplus.clean.application;

import com.hhplus.clean.domain.dto.*;
import com.hhplus.clean.domain.service.LectureEnrollService;
import com.hhplus.clean.domain.service.LectureScheduleService;
import com.hhplus.clean.domain.service.UserService;

import com.hhplus.clean.interfaces.api.lecture.LectureController;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LectureFacade {
    private static final Logger log = LoggerFactory.getLogger(LectureFacade.class);
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
    public LectureEnrollInfo enrollLecture(Long lectureId, UserCommand userCommand) {

        // 사용자 확인
        UserCommand user = UserCommand.from(userService.getUser(userCommand.id()));
        log.info("user: {}", user);
        // 특강 스케줄 확인
        LectureScheduleCommand lectureSchedule = LectureScheduleCommand.from(lectureScheduleService.getLectureScheduleById(lectureId)) ;
        log.info("lectureSchedule: {}", lectureSchedule);

        lectureEnrollService.checkEnroll(user, lectureSchedule);
        lectureScheduleService.checkCapacity(lectureSchedule);

        // 특강 신청 처리
        LectureEnrollCommand lectureEnroll = LectureEnrollCommand.from(lectureEnrollService.saveEnroll(user, lectureSchedule));
        LectureScheduleCommand lectureScheduleIncrement = LectureScheduleCommand.from(lectureScheduleService.incrementCurrentCount(lectureSchedule));
        lectureScheduleService.saveLectureSchedule(lectureScheduleIncrement);
        LectureEnrollInfo lectureEnrollInfo = lectureEnrollService.findById(lectureEnroll);

        log.info("LectureEnrollInfo: {}", lectureEnrollInfo.lectureSchedule());

        return lectureEnrollInfo;
    }
}
