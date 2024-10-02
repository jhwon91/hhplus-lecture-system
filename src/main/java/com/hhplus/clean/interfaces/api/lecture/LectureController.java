package com.hhplus.clean.interfaces.api.lecture;

import com.hhplus.clean.application.LectureFacade;
import com.hhplus.clean.domain.lectureSchedule.LectureSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;
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
    public List<LectureResponseDTO> getLecturesByDate(@RequestParam("date") LocalDate date) {
        LectureRequestDTO requestDTO = new LectureRequestDTO(date);
        List<LectureSchedule> schedules = lectureFacade.getAvailableLectureSchedules(requestDTO.getDate());
        log.info("schedules: {}", schedules);
        List<LectureResponseDTO> responseDTO = schedules.stream()
                .map(LectureResponseDTO::from)
                .collect(Collectors.toList());

        return responseDTO;
    }
}
