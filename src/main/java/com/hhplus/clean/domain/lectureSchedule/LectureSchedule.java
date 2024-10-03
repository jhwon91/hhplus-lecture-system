package com.hhplus.clean.domain.lectureSchedule;

import com.hhplus.clean.infrastructure.entity.LectureEntity;
import com.hhplus.clean.infrastructure.entity.LectureScheduleEntity;
import com.hhplus.clean.infrastructure.entity.UserEntity;

import java.time.LocalDate;

public class LectureSchedule {
    private Long id;
    private LectureEntity lectureEntity;
    private LocalDate regDate;
    private int capacity;
    private int currentCount;

    // 기본 생성자
    public LectureSchedule() {}

    // 생성자
    public LectureSchedule(Long id, LectureEntity lectureEntity, LocalDate regDate, int capacity, int currentCount) {
        this.id = id;
        this.lectureEntity = lectureEntity;
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

    public LectureEntity getLectureEntity() {
        return lectureEntity;
    }

    public void setLectureEntity(LectureEntity lectureEntity) {
        this.lectureEntity = lectureEntity;
    }

    public static LectureSchedule from(LectureScheduleEntity entity) {
        return new LectureSchedule(
                entity.getId(),
                entity.getLectureEntity(),
                entity.getRegDate(),
                entity.getCapacity(),
                entity.getLectureEnrolls().size()
        );
    }

    public LectureScheduleEntity toEntity() {
        return new LectureScheduleEntity(
          this.id,
          this.lectureEntity,
          this.regDate,
          this.capacity,
          this.currentCount
        );
    }


}
