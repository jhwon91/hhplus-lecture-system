package com.hhplus.clean.interfaces.api.lecture;

import com.hhplus.clean.domain.lectureSchedule.LectureSchedule;

import java.time.LocalDate;

public class LectureRequestDTO {
    private LocalDate date;

    public LectureRequestDTO() {}

    public LectureRequestDTO(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
