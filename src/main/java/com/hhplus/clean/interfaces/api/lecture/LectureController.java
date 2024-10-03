package com.hhplus.clean.interfaces.api.lecture;

import com.hhplus.clean.application.LectureFacade;
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
    public String registerLecture(@PathVariable Long lectureId,
                                  @RequestBody UserRequestDTO requestDTO) {
        lectureFacade.enrollLecture(lectureId, requestDTO.toCommand() );
        return "특강 신청이 완료되었습니다.";
    }
}
