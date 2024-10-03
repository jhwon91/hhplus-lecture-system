package com.hhplus.clean.interfaces.api.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.clean.domain.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserFacade userFacade;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void 사용자_생성() throws Exception {
        //given
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUsername("testUser");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testUser");

        when(userFacade.saveUser(any(UserEntity.class))).thenReturn(userEntity);

        //when
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testUser"));

        //then
        verify(userFacade, times(1)).saveUser(any(UserEntity.class));
    }

    @Test
    void 단일_사용자_조회() throws Exception {
        //given
        long userId = 1L;

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setUsername("testUser");

        when(userFacade.getUser(userId)).thenReturn(userEntity);

        //when
        mockMvc.perform(get("/users/{id}",userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testUser"));

        //then
        verify(userFacade, times(1)).getUser(userId);
    }

    @Test
    void 사용자_목록_조회() throws Exception {
        //given

        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setUsername("testUser1");

        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        user2.setUsername("testUser2");

        UserEntity user3 = new UserEntity();
        user3.setId(3L);
        user3.setUsername("testUser3");

        when(userFacade.getAllUsers()).thenReturn(List.of(user1,user2,user3));

        //when
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].username").value("testUser1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].username").value("testUser2"))
                .andExpect(jsonPath("$[2].id").value(3L))
                .andExpect(jsonPath("$[2].username").value("testUser3"));

        //then
        verify(userFacade, times(1)).getAllUsers();
    }

}
