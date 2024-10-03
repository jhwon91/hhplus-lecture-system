package com.hhplus.clean.domain.service;

import com.hhplus.clean.domain.Repository.LectureEnrollRepository;
import com.hhplus.clean.domain.dto.LectureScheduleCommand;
import com.hhplus.clean.domain.dto.UserCommand;
import com.hhplus.clean.domain.entity.LectureEnrollEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LectureEnrollService {

    private final LectureEnrollRepository lectureEnrollRepository;

    public LectureEnrollService(LectureEnrollRepository lectureEnrollRepository) {
        this.lectureEnrollRepository = lectureEnrollRepository;
    }

    // 새로운 신청 등록
    public void saveEnroll(UserCommand user, LectureScheduleCommand lectureSchedule) {

        LectureEnrollEntity lectureEnroll = new LectureEnrollEntity();
        lectureEnroll.setUserEntity(user.toEntity());
        lectureEnroll.setLectureScheduleEntity(lectureSchedule.toEntity());
        lectureEnroll.setEnrollTime(LocalDateTime.now());

        lectureEnrollRepository.save(lectureEnroll);
    }

    // 이미 신청했는지 확인
    public void checkEnroll(UserCommand user, LectureScheduleCommand lectureSchedule) {
        if (lectureEnrollRepository.existsByUserAndLectureSchedule(user.toEntity(), lectureSchedule.toEntity())) {
            throw new RuntimeException("이미 해당 특강에 신청하셨습니다.");
        }
    }

}
