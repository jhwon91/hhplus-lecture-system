package com.hhplus.clean.domain.lectureEnroll;

import com.hhplus.clean.domain.lectureSchedule.LectureSchedule;
import com.hhplus.clean.domain.user.UserCommand;
import com.hhplus.clean.domain.user.UserInfo;
import com.hhplus.clean.infrastructure.entity.LectureEnrollEntity;
import com.hhplus.clean.infrastructure.entity.LectureScheduleEntity;
import com.hhplus.clean.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LectureEnrollService {

    private final LectureEnrollRepository lectureEnrollRepository;

    public LectureEnrollService(LectureEnrollRepository lectureEnrollRepository) {
        this.lectureEnrollRepository = lectureEnrollRepository;
    }

    // 새로운 신청 등록
    public void saveEnroll(UserCommand user, LectureSchedule lectureSchedule) {

        LectureEnrollEntity lectureEnroll = new LectureEnrollEntity();
        lectureEnroll.setUserEntity(user.toEntity());
        lectureEnroll.setLectureScheduleEntity(lectureSchedule.toEntity());
        lectureEnroll.setEnrollTime(LocalDateTime.now());

        lectureEnrollRepository.save(lectureEnroll);
    }

    // 이미 신청했는지 확인
    public void checkEnroll(UserCommand user, LectureSchedule lectureSchedule) {
        if (lectureEnrollRepository.existsByUserAndLectureSchedule(user.toEntity(), lectureSchedule.toEntity())) {
            throw new RuntimeException("이미 해당 특강에 신청하셨습니다.");
        }
    }

}
