package com.hhplus.clean.domain.lectureSchedule;

import com.hhplus.clean.domain.lecture.LectureEntity;
import com.hhplus.clean.domain.lectureEnroll.LectureEnrollEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)
    private LectureEntity lectureEntity;

    @Column(name = "reg_date", nullable = false)
    private LocalDate regDate;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "current_count")
    private int currentCount;

    @OneToMany(mappedBy = "lectureScheduleEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureEnrollEntity> lectureEnrolls = new ArrayList<>();

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
