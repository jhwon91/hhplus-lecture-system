package com.hhplus.clean.interfaces.api.controller;

import com.hhplus.clean.application.LectureFacade;
import com.hhplus.clean.interfaces.api.dto.LectureEnrollResponseDTO;
import com.hhplus.clean.interfaces.api.dto.LectureScheduleResponseDTO;
import com.hhplus.clean.interfaces.api.dto.UserRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lecture")
public class LectureController {

    private static final Logger log = LoggerFactory.getLogger(LectureController.class);
    private final LectureFacade lectureFacade;

    public LectureController(LectureFacade lectureFacade) {
        this.lectureFacade = lectureFacade;
    }

    @GetMapping
    public List<LectureScheduleResponseDTO> getLecturesByDate(@RequestParam("date") LocalDate date) {
        return lectureFacade.getAvailableLectureSchedules(date).stream()
                .map(LectureScheduleResponseDTO::from)
                .collect(Collectors.toList());
    }

    // 특강 신청 API
    @PostMapping("/{lectureId}/register")
    public LectureEnrollResponseDTO registerLecture(@PathVariable Long lectureId,
                                                    @RequestBody UserRequestDTO requestDTO) {
        return LectureEnrollResponseDTO.from(
                lectureFacade.enrollLecture(lectureId, requestDTO.toCommand())
        );
    }

    // 사용자가 신청한 특강 목록 조회 API
    @GetMapping("/{userId}/lectures")
    public List<LectureEnrollResponseDTO> getUserLectures(@PathVariable Long userId) {
        return lectureFacade.getUserLectures(userId).stream()
                .map(LectureEnrollResponseDTO::from)
                .collect(Collectors.toList());
    }
}
