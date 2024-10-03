package com.hhplus.clean.domain.service;

import com.hhplus.clean.domain.Repository.LectureEnrollRepository;
import com.hhplus.clean.domain.dto.*;
import com.hhplus.clean.domain.entity.LectureEnrollEntity;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureEnrollService {

    private final LectureEnrollRepository lectureEnrollRepository;

    public LectureEnrollService(LectureEnrollRepository lectureEnrollRepository) {
        this.lectureEnrollRepository = lectureEnrollRepository;
    }

    // 새로운 신청 등록
    @Transactional
    public LectureEnrollInfo saveEnroll(UserCommand user, LectureScheduleCommand lectureSchedule) {

        LectureEnrollEntity lectureEnroll = new LectureEnrollEntity();
        lectureEnroll.setUserEntity(user.toEntity());
        lectureEnroll.setLectureScheduleEntity(lectureSchedule.toEntity());
        lectureEnroll.setEnrollTime(LocalDateTime.now());

        return LectureEnrollInfo.from(lectureEnrollRepository.save(lectureEnroll));

    }

    // 이미 신청했는지 확인
    public void checkEnroll(UserCommand user, LectureScheduleCommand lectureSchedule) {
        if (lectureEnrollRepository.existsByUserAndLectureSchedule(user.toEntity(), lectureSchedule.toEntity())) {
            throw new IllegalArgumentException("이미 해당 특강에 신청하셨습니다.");
        }
    }

    public List<LectureEnrollInfo> findByUser(UserCommand userCommand) {
        return lectureEnrollRepository.findByUserEntity(userCommand.toEntity()).stream()
                .map(LectureEnrollInfo::from)
                .collect(Collectors.toList());
    }


    public LectureEnrollInfo findById(LectureEnrollCommand lectureEnrollCommand) {
        return LectureEnrollInfo.from(
                lectureEnrollRepository.findById(lectureEnrollCommand.id())
                    .orElseThrow(() -> new IllegalArgumentException("등록을 찾을 수 없습니다."))
        );
    }

}
