package com.hhplus.clean.domain.user;

import com.hhplus.clean.domain.lectureEnroll.LectureEnrollEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @OneToMany(mappedBy = "user")
    private List<LectureEnrollEntity> lectureEnrolls = new ArrayList<>();

    public UserEntity() {}
    public UserEntity(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<LectureEnrollEntity> getLectureEnrolls() {
        return lectureEnrolls;
    }

    public void setLectureEnrolls(List<LectureEnrollEntity> lectureEnrolls) {
        this.lectureEnrolls = lectureEnrolls;
    }
}
