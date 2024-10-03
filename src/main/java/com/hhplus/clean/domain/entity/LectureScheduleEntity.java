package com.hhplus.clean.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lecture_schedule")
public class LectureScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lecture_id", nullable = false)
    private LectureEntity lectureEntity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "reg_date", nullable = false)
    private LocalDate regDate;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "current_count")
    private int currentCount;

    @JsonIgnore
    @OneToMany(mappedBy = "lectureScheduleEntity")
    private List<LectureEnrollEntity> lectureEnrolls = new ArrayList<>();

    public LectureScheduleEntity() {}
    public LectureScheduleEntity(Long id, LectureEntity lectureEntity, LocalDate regDate, int capacity, int currentCount) {
        this.id = id;
        this.lectureEntity = lectureEntity;
        this.regDate = regDate;
        this.capacity = capacity;
        this.currentCount = currentCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LectureEntity getLectureEntity() {
        return lectureEntity;
    }

    public void setLectureEntity(LectureEntity lectureEntity) {
        this.lectureEntity = lectureEntity;
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

    public List<LectureEnrollEntity> getLectureEnrolls() {
        return lectureEnrolls;
    }

    public void setLectureEnrolls(List<LectureEnrollEntity> lectureEnrolls) {
        this.lectureEnrolls = lectureEnrolls;
    }
}
