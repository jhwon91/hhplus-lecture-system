package com.hhplus.clean.domain.service;

import com.hhplus.clean.domain.Repository.LectureScheduleRepository;
import com.hhplus.clean.domain.dto.LectureScheduleCommand;
import com.hhplus.clean.domain.dto.LectureScheduleInfo;
import com.hhplus.clean.domain.entity.LectureEntity;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LectureScheduleServiceTest {

    @Mock
    private LectureScheduleRepository lectureScheduleRepository;

    @InjectMocks
    private LectureScheduleService lectureScheduleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 존재하는ID_성공() {
        // Given
        Long lectureScheduleId = 1L;
        LectureScheduleEntity lectureScheduleEntity = createLectureScheduleEntity(lectureScheduleId);

        when(lectureScheduleRepository.findById(lectureScheduleId)).thenReturn(Optional.of(lectureScheduleEntity));

        // When
        LectureScheduleInfo result = lectureScheduleService.getLectureScheduleById(lectureScheduleId);

        // Then
        assertNotNull(result);
        assertEquals(lectureScheduleId, result.id());
    }

    @Test
    void 존재하지않는ID_예외발생() {
        // Given
        Long lectureScheduleId = 999L;
        when(lectureScheduleRepository.findById(lectureScheduleId)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            lectureScheduleService.getLectureScheduleById(lectureScheduleId);
        });

        assertEquals("특강 일정을 찾을 수 없습니다.", exception.getMessage());
    }

    @Test
    void 사용가능한스케줄존재_성공() {
        // Given
        LocalDate date = LocalDate.of(2024, 10, 3);
        LectureScheduleEntity schedule1 = createLectureScheduleEntity(1L);
        schedule1.setCapacity(30);

        LectureScheduleEntity schedule2 = createLectureScheduleEntity(2L);
        schedule2.setCapacity(30);

        when(lectureScheduleRepository.findByRegDate(date)).thenReturn(Arrays.asList(schedule1, schedule2));

        // When
        List<LectureScheduleInfo> result = lectureScheduleService.getAvailableLectureSchedules(date);

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void 정원초과_예외발생() {
        // Given
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(1L);
        lectureEntity.setLectureName("test");

        LectureScheduleCommand lectureSchedule = new LectureScheduleCommand(1L, lectureEntity, LocalDate.now(), 10,20 );

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            lectureScheduleService.checkCapacity(lectureSchedule);
        });

        assertEquals("특강 정원이 가득 찼습니다.", exception.getMessage());
    }

    @Test
    void 정원미달_정상동작() {
        // Given
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(1L);
        lectureEntity.setLectureName("test");
        LectureScheduleCommand lectureSchedule = new LectureScheduleCommand(1L, lectureEntity,  LocalDate.now(), 30, 29);

        // When & Then
        assertDoesNotThrow(() -> {
            lectureScheduleService.checkCapacity(lectureSchedule);
        });
    }

    @Test
    void 수강인원_증가_성공() {
        // Given
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(1L);
        lectureEntity.setLectureName("test");
        LectureScheduleCommand lectureScheduleCommand = new LectureScheduleCommand(1L, lectureEntity,LocalDate.now(),30,10);

        LectureScheduleEntity lectureScheduleEntity = createLectureScheduleEntity(1L);
        lectureScheduleEntity.setCurrentCount(10);

        when(lectureScheduleRepository.findById(1L)).thenReturn(Optional.of(lectureScheduleEntity));

        // When
        LectureScheduleInfo result = lectureScheduleService.incrementCurrentCount(lectureScheduleCommand);

        // Then
        assertNotNull(result);
        assertEquals(11, result.currentCount());
    }


    @Test
    void saveLectureSchedule_저장성공() {
        // Given
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(1L);
        lectureEntity.setLectureName("test");
        LectureScheduleCommand lectureScheduleCommand = new LectureScheduleCommand(1L, lectureEntity,LocalDate.now(),30,10);

        LectureScheduleEntity lectureScheduleEntity = lectureScheduleCommand.toEntity();

        when(lectureScheduleRepository.save(any(LectureScheduleEntity.class))).thenReturn(lectureScheduleEntity);

        // When
        LectureScheduleInfo result = lectureScheduleService.saveLectureSchedule(lectureScheduleCommand);

        // Then
        assertEquals(lectureScheduleCommand.id(), result.id());
    }

    // 헬퍼 메서드
    private LectureScheduleEntity createLectureScheduleEntity(Long id) {
        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(id);
        lectureEntity.setLectureName("test");

        LectureScheduleEntity lectureScheduleEntity = new LectureScheduleEntity();
        lectureScheduleEntity.setId(id);
        lectureScheduleEntity.setLectureEntity(lectureEntity);
        lectureScheduleEntity.setRegDate(LocalDate.of(2024, 10, 3));
        lectureScheduleEntity.setCapacity(30);
        lectureScheduleEntity.setCurrentCount(0);

        return lectureScheduleEntity;
    }
}