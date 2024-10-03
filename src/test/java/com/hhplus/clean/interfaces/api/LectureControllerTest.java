package com.hhplus.clean.interfaces.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.clean.application.LectureFacade;
import com.hhplus.clean.domain.dto.LectureEnrollInfo;
import com.hhplus.clean.domain.dto.LectureScheduleInfo;
import com.hhplus.clean.domain.dto.UserCommand;
import com.hhplus.clean.domain.entity.LectureEntity;
import com.hhplus.clean.domain.entity.LectureScheduleEntity;
import com.hhplus.clean.domain.entity.UserEntity;
import com.hhplus.clean.interfaces.api.controller.LectureController;
import com.hhplus.clean.interfaces.api.dto.UserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LectureControllerTest {
    private MockMvc mockMvc;

    @Mock
    private LectureFacade lectureFacade;

    @InjectMocks
    private LectureController lectureController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(lectureController).build();
    }

    @Test
    void 특강_신청() throws Exception {
        //given
        Long lectureId = 1L;

        UserRequestDTO requestDTO = new UserRequestDTO(1L,"testuser");

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("testuser");

        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(1L);
        lectureEntity.setLectureName("test");

        LectureScheduleEntity lectureSchedule = new LectureScheduleEntity();
        lectureSchedule.setId(1L);
        lectureSchedule.setLectureEntity(lectureEntity);
        lectureSchedule.setRegDate(LocalDate.of(2024, 10, 3));
        lectureSchedule.setCapacity(30);
        lectureSchedule.setCurrentCount(0);

        LocalDateTime enrollTime = LocalDateTime.of(2024, 10, 4, 1, 47, 22, 675055000);
        LectureEnrollInfo lectureEnrollInfo = LectureEnrollInfo.builder()
                .id(1L)
                .user(user)
                .lectureSchedule(lectureSchedule)
                .enrollTime(enrollTime)
                .build();

        //when
        when(lectureFacade.enrollLecture(any(Long.class), any(UserCommand.class)))
                .thenReturn(lectureEnrollInfo);

        // Then
        mockMvc.perform(post("/lecture/" + lectureId + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.user.id").value(1))
                .andExpect(jsonPath("$.user.username").value("testuser"))
                .andExpect(jsonPath("$.lectureSchedule.id").value(1))
                .andExpect(jsonPath("$.lectureSchedule.lectureEntity.id").value(1))
                .andExpect(jsonPath("$.lectureSchedule.lectureEntity.lectureName").value("test"))
                .andExpect(jsonPath("$.lectureSchedule.regDate").value("2024-10-03"))
                .andExpect(jsonPath("$.lectureSchedule.capacity").value(30))
                .andExpect(jsonPath("$.lectureSchedule.currentCount").value(0))
                .andExpect(jsonPath("$.enrollTime").value("2024-10-04T01:47:22.675055"));

        verify(lectureFacade, times(1)).enrollLecture(any(),any(UserCommand.class));
    }

    @Test
    void 특정_날짜에_사용가능한_특강_조회() throws Exception {
        // given
        LocalDate date = LocalDate.of(2024, 10, 3);

        LectureEntity lectureEntity1 = new LectureEntity();
        lectureEntity1.setId(1L);
        lectureEntity1.setLectureName("test1");

        LectureEntity lectureEntity2 = new LectureEntity();
        lectureEntity2.setId(2L);
        lectureEntity2.setLectureName("test2");

        LectureScheduleInfo lectureScheduleInfo = new LectureScheduleInfo(
                1L,
                lectureEntity1,
                date,
                30,
                0
        );

        LectureScheduleInfo lectureScheduleInfo2 = new LectureScheduleInfo(
                2L,
                lectureEntity2,
                date,
                50,
                0
        );

        List<LectureScheduleInfo> responseList = List.of(lectureScheduleInfo, lectureScheduleInfo2);

        // when
        when(lectureFacade.getAvailableLectureSchedules(any(LocalDate.class))).thenReturn(responseList);

        // then
        mockMvc.perform(get("/lecture")
                .param("date", "2024-10-03")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].lecture.lectureName").value("test1"))
                .andExpect(jsonPath("$[0].capacity").value(30))
                .andExpect(jsonPath("$[0].currentCount").value(0))
                .andExpect(jsonPath("$[0].regDate").value("2024-10-03"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].lecture.lectureName").value("test2"))
                .andExpect(jsonPath("$[1].capacity").value(50))
                .andExpect(jsonPath("$[1].currentCount").value(0))
                .andExpect(jsonPath("$[1].regDate").value("2024-10-03"));

        verify(lectureFacade, times(1)).getAvailableLectureSchedules(any(LocalDate.class));
    }

    @Test
    void 특정_사용자의_강의_목록_조회() throws Exception {
        //given
        Long userId = 1L;

        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setUsername("testuser");

        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setId(1L);
        lectureEntity.setLectureName("test1");

        LectureScheduleEntity lectureSchedule1 = new LectureScheduleEntity();
        lectureSchedule1.setId(1L);
        lectureSchedule1.setLectureEntity(lectureEntity);
        lectureSchedule1.setRegDate(LocalDate.of(2024, 10, 3));
        lectureSchedule1.setCapacity(30);
        lectureSchedule1.setCurrentCount(0);

        LectureScheduleEntity lectureSchedule2 = new LectureScheduleEntity();
        lectureSchedule2.setId(2L);
        lectureSchedule2.setLectureEntity(lectureEntity);
        lectureSchedule2.setRegDate(LocalDate.of(2024, 10, 4));
        lectureSchedule2.setCapacity(30);
        lectureSchedule2.setCurrentCount(0);

        LocalDateTime enrollTime = LocalDateTime.of(2024, 10, 4, 1, 47, 22, 675055000);
        LectureEnrollInfo lectureEnrollInfo1 = LectureEnrollInfo.builder()
                .id(1L)
                .user(user)
                .lectureSchedule(lectureSchedule1)
                .enrollTime(enrollTime)
                .build();
        LectureEnrollInfo lectureEnrollInfo2 = LectureEnrollInfo.builder()
                .id(2L)
                .user(user)
                .lectureSchedule(lectureSchedule2)
                .enrollTime(enrollTime)
                .build();


        List<LectureEnrollInfo> responseList = List.of(lectureEnrollInfo1, lectureEnrollInfo2);

        when(lectureFacade.getUserLectures(userId)).thenReturn(responseList);

        //when
        mockMvc.perform(get("/lecture/"+ userId +"/lectures", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].lectureSchedule.lectureEntity.lectureName").value("test1"))
                .andExpect(jsonPath("$[0].lectureSchedule.regDate").value("2024-10-03"))

                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].lectureSchedule.lectureEntity.lectureName").value("test1"))
                .andExpect(jsonPath("$[1].lectureSchedule.regDate").value("2024-10-04"));

        //then
        verify(lectureFacade, times(1)).getUserLectures(userId);
    }
}
