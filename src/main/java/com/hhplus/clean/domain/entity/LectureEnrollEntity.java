package com.hhplus.clean.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lecture_enroll")
public class LectureEnrollEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_enroll_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_schedule_id", nullable = false)
    private LectureScheduleEntity lectureScheduleEntity;

    @Column(name = "enroll_time")
    private LocalDateTime enrollTime;

    public LectureEnrollEntity() {}

    public LectureEnrollEntity(Long id, UserEntity userEntity, LectureScheduleEntity lectureScheduleEntity, LocalDateTime enrollTime) {
        this.id = id;
        this.userEntity = userEntity;
        this.lectureScheduleEntity = lectureScheduleEntity;
        this.enrollTime = enrollTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public LectureScheduleEntity getLectureScheduleEntity() {
        return lectureScheduleEntity;
    }

    public void setLectureScheduleEntity(LectureScheduleEntity lectureScheduleEntity) {
        this.lectureScheduleEntity = lectureScheduleEntity;
    }

    public LocalDateTime getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(LocalDateTime enrollTime) {
        this.enrollTime = enrollTime;
    }
}
