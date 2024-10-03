package com.hhplus.clean.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lecture")
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    @Column(name = "lecture_name", nullable = false)
    private String lectureName;

    @JsonIgnore // 직렬화 시 이 필드를 무시함
    @OneToMany(mappedBy = "lectureEntity")
    private List<LectureScheduleEntity> lectureSchedules = new ArrayList<>();

    public LectureEntity() {}

    public LectureEntity(Long id, String lectureName, List<LectureScheduleEntity> lectureSchedules) {
        this.id = id;
        this.lectureName = lectureName;
        this.lectureSchedules = lectureSchedules;
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

    public List<LectureScheduleEntity> getLectureSchedules() {
        return lectureSchedules;
    }

    public void setLectureSchedules(List<LectureScheduleEntity> lectureSchedules) {
        this.lectureSchedules = lectureSchedules;
    }
}
