package com.hhplus.clean.interfaces.api.lecture;

import com.hhplus.clean.domain.lectureSchedule.LectureSchedule;

import java.time.LocalDate;

public class LectureResponseDTO {
    private Long id;
    private String lectureName;
    private LocalDate regDate;
    private int capacity;
    private int currentCount;

    public LectureResponseDTO() {}

    public LectureResponseDTO(Long id, String lectureName, LocalDate regDate, int capacity, int currentCount) {
        this.id = id;
        this.lectureName = lectureName;
        this.regDate = regDate;
        this.capacity = capacity;
        this.currentCount = currentCount;
    }

    // 엔티티에서 Response DTO로 변환하는 메소드
    public static LectureResponseDTO from(LectureSchedule lectureSchedule) {
        return new LectureResponseDTO(
                lectureSchedule.getId(),
                lectureSchedule.getLectureEntity().getLectureName(),
                lectureSchedule.getRegDate(),
                lectureSchedule.getCapacity(),
                lectureSchedule.getCurrentCount()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }
}
