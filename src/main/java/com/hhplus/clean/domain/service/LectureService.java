package com.hhplus.clean.domain.service;

import com.hhplus.clean.domain.Repository.LectureRepository;
import com.hhplus.clean.domain.dto.LectureCommand;
import com.hhplus.clean.domain.dto.LectureInfo;
import org.springframework.stereotype.Service;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public LectureInfo saveLecture(LectureCommand lecture) {
        return LectureInfo.from(lectureRepository.save(lecture.toEntity()));
    }
}
