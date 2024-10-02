package com.hhplus.clean.domain.lectureSchedule;

import java.time.LocalDate;

public class LectureSchedule {
    private Long id;
    private String lectureName;
    private LocalDate regDate;
    private int capacity;
    private int currentCount;

    // 기본 생성자
    public LectureSchedule() {}

    // 생성자
    public LectureSchedule(Long id, String lectureName, LocalDate regDate, int capacity, int currentCount) {
        this.id = id;
        this.lectureName = lectureName;
        this.regDate = regDate;
        this.capacity = capacity;
        this.currentCount = currentCount;
    }

    // Getter와 Setter
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

    // LectureScheduleEntity에서 LectureSchedule로 변환하는 메소드
    public static LectureSchedule from(LectureScheduleEntity entity) {
        return new LectureSchedule(
                entity.getId(),
                entity.getLectureEntity().getLectureName(),
                entity.getRegDate(),
                entity.getCapacity(),
                entity.getLectureEnrolls().size()
        );
    }
}
