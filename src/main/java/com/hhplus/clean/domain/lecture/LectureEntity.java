package com.hhplus.clean.domain.lecture;

import com.hhplus.clean.domain.lectureSchedule.LectureScheduleEntity;
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

    @Column(name = "lecture_id", nullable = false)
    private String lectureName;

    @OneToMany(mappedBy = "lecture")
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
