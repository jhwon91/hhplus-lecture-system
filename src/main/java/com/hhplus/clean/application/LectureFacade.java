package com.hhplus.clean.application;

import com.hhplus.clean.domain.lectureEnroll.LectureEnrollService;
import com.hhplus.clean.domain.lectureSchedule.LectureSchedule;
import com.hhplus.clean.domain.lectureSchedule.LectureScheduleService;
import com.hhplus.clean.domain.user.UserCommand;
import com.hhplus.clean.domain.user.UserService;

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
    public List<LectureSchedule> getAvailableLectureSchedules(LocalDate date) {
        return lectureScheduleService.getAvailableLectureSchedules(date);
    }

    @Transactional
    public void enrollLecture(Long lectureId, UserCommand userCommand) {

        // 사용자 확인
        UserCommand user = UserCommand.form(userService.getUser(userCommand.getId())) ;

        // 특강 스케줄 확인
        LectureSchedule lectureSchedule = lectureScheduleService.getLectureScheduleById(lectureId);

        lectureEnrollService.checkEnroll(user, lectureSchedule);
        lectureScheduleService.checkCapacity(lectureSchedule);

        // 특강 신청 처리
        lectureEnrollService.saveEnroll(user, lectureSchedule);
        lectureScheduleService.incrementCurrentCount(lectureSchedule);
        lectureScheduleService.saveLectureSchedule(lectureSchedule);

    }
}
