package com.hhplus.clean.domain;

import com.hhplus.clean.domain.Repository.LectureEnrollRepository;
import com.hhplus.clean.domain.dto.LectureEnrollCommand;
import com.hhplus.clean.domain.dto.LectureEnrollInfo;
import com.hhplus.clean.domain.dto.LectureScheduleCommand;
import com.hhplus.clean.domain.dto.UserCommand;
import com.hhplus.clean.domain.entity.LectureEnrollEntity;
import com.hhplus.clean.domain.entity.LectureEntity;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import com.hhplus.clean.domain.entity.UserEntity;
import com.hhplus.clean.domain.service.LectureEnrollService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LectureEnrollServiceTest {

    @Mock
    private LectureEnrollRepository lectureEnrollRepository;

    @InjectMocks
    private LectureEnrollService lectureEnrollService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 신청저장_성공() {
        // Given

        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(1L);
        lectureEntity.setLectureName("test");

        UserCommand userCommand = new UserCommand(1L, "testuser");
        LectureScheduleCommand lectureScheduleCommand = new LectureScheduleCommand(
                1L,
                lectureEntity,
                LocalDate.of(2024, 10, 3),
                30,
                0
        );

        UserEntity userEntity = userCommand.toEntity();
        LectureScheduleEntity lectureScheduleEntity = lectureScheduleCommand.toEntity();

        LectureEnrollEntity lectureEnrollEntity = new LectureEnrollEntity();
        lectureEnrollEntity.setId(1L);
        lectureEnrollEntity.setUserEntity(userEntity);
        lectureEnrollEntity.setLectureScheduleEntity(lectureScheduleEntity);
        lectureEnrollEntity.setEnrollTime(LocalDateTime.now());

        when(lectureEnrollRepository.save(any(LectureEnrollEntity.class))).thenReturn(lectureEnrollEntity);

        // When
        LectureEnrollInfo result = lectureEnrollService.saveEnroll(userCommand, lectureScheduleCommand);

        // Then
        assertEquals(1L, result.id());
        assertEquals(userEntity, result.user());
        assertEquals(lectureScheduleEntity, result.lectureSchedule());
    }

    @Test
    void 이미신청됨_예외발생() {
        // Given
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(1L);
        lectureEntity.setLectureName("test");

        UserCommand userCommand = new UserCommand(1L, "testuser");
        LectureScheduleCommand lectureScheduleCommand = new LectureScheduleCommand(
                1L,
                lectureEntity,
                LocalDate.of(2024, 10, 3),
                30,
                0
        );

        when(lectureEnrollRepository.existsByUserAndLectureSchedule(any(UserEntity.class), any(LectureScheduleEntity.class)))
                .thenReturn(true);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            lectureEnrollService.checkEnroll(userCommand, lectureScheduleCommand);
        });

        assertEquals("이미 해당 특강에 신청하셨습니다.", exception.getMessage());
    }

    @Test
    void 신청목록조회_성공() {
        // Given
        UserCommand userCommand = new UserCommand(1L, "testuser");
        UserEntity userEntity = userCommand.toEntity();

        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(1L);
        lectureEntity.setLectureName("test");

        LectureScheduleCommand lectureScheduleCommand1 = new LectureScheduleCommand(
                1L,
                lectureEntity,
                LocalDate.of(2024, 10, 3),
                30,
                0
        );

        LectureScheduleCommand lectureScheduleCommand2 = new LectureScheduleCommand(
                1L,
                lectureEntity,
                LocalDate.of(2024, 10, 3),
                30,
                0
        );

        LectureScheduleEntity LectureScheduleEntity1 = lectureScheduleCommand1.toEntity();
        LectureScheduleEntity LectureScheduleEntity2 = lectureScheduleCommand2.toEntity();

        LectureEnrollEntity enroll1 = new LectureEnrollEntity();
        enroll1.setId(1L);
        enroll1.setUserEntity(userEntity);
        enroll1.setLectureScheduleEntity(LectureScheduleEntity1);
        enroll1.setEnrollTime(LocalDateTime.now());

        LectureEnrollEntity enroll2 = new LectureEnrollEntity();
        enroll2.setId(2L);
        enroll2.setUserEntity(userEntity);
        enroll1.setLectureScheduleEntity(LectureScheduleEntity2);
        enroll2.setEnrollTime(LocalDateTime.now());

        when(lectureEnrollRepository.findByUserEntity(any(UserEntity.class)))
                .thenReturn(Arrays.asList(enroll1, enroll2));

        // When
        List<LectureEnrollInfo> result = lectureEnrollService.findByUser(userCommand);

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void 존재하는ID_성공() {
        // Given

        UserCommand userCommand = new UserCommand(1L, "testuser");
        UserEntity userEntity = userCommand.toEntity();

        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(1L);
        lectureEntity.setLectureName("test");

        LectureScheduleCommand lectureScheduleCommand = new LectureScheduleCommand(
                1L,
                lectureEntity,
                LocalDate.of(2024, 10, 3),
                30,
                0
        );
        LectureScheduleEntity LectureScheduleEntity = lectureScheduleCommand.toEntity();

        LectureEnrollCommand lectureEnrollCommand = new LectureEnrollCommand(
                1L,
                userEntity,
                LectureScheduleEntity,
                LocalDateTime.now()
        );

        LectureEnrollEntity lectureEnrollEntity =lectureEnrollCommand.toEntity();

        when(lectureEnrollRepository.findById(1L)).thenReturn(Optional.of(lectureEnrollEntity));

        // When
        LectureEnrollInfo result = lectureEnrollService.findById(lectureEnrollCommand);

        // Then
        assertEquals(1L, result.id());
        assertEquals(userEntity, result.user());
        assertEquals(LectureScheduleEntity, result.lectureSchedule());
    }

    @Test
    void 존재하지않는ID_예외발생() {
        // Given
        UserCommand userCommand = new UserCommand(1L, "testuser");
        UserEntity userEntity = userCommand.toEntity();

        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(1L);
        lectureEntity.setLectureName("test");

        LectureScheduleCommand lectureScheduleCommand = new LectureScheduleCommand(
                1L,
                lectureEntity,
                LocalDate.of(2024, 10, 3),
                30,
                0
        );
        LectureScheduleEntity LectureScheduleEntity = lectureScheduleCommand.toEntity();

        LectureEnrollCommand lectureEnrollCommand = new LectureEnrollCommand(
                999L,
                userEntity,
                LectureScheduleEntity,
                LocalDateTime.now()
        );


        when(lectureEnrollRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            lectureEnrollService.findById(lectureEnrollCommand);
        });

        assertEquals("등록을 찾을 수 없습니다.", exception.getMessage());
    }
}
