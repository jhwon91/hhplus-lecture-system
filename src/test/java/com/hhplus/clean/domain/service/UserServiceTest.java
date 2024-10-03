package com.hhplus.clean.domain.service;

import com.hhplus.clean.domain.dto.UserCommand;
import com.hhplus.clean.domain.dto.UserInfo;
import com.hhplus.clean.domain.entity.UserEntity;
import com.hhplus.clean.domain.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void 사용자_생성(){
        //given
        UserCommand user = new UserCommand(1L,"testUser");

        UserEntity saveUser = new UserEntity();
        saveUser.setId(1L);
        saveUser.setUsername("testUser");

        when(userRepository.save(any(UserEntity.class))).thenReturn(saveUser);

        //when
        UserInfo result = userService.saveUser(user);

        //then
        assertEquals(1L, result.id());
        assertEquals("testUser", result.userName());
    }

    @Test
    void 단일_사용자_조회() {
        //given
        long userId = 1L;
        UserCommand user_1 = new UserCommand(userId,"testUser");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user_1.toEntity()));

        //when
        UserInfo result = userService.getUser(userId);

        //then
        assertEquals(userId, result.id());
        assertEquals("testUser", result.userName());
    }

    @Test
    void 사용자_목록_조회() {
        //given
        UserCommand user_1 = new UserCommand(1L,"testUser");
        UserCommand user_2 = new UserCommand(2L,"testUser2");
        UserCommand user_3 = new UserCommand(3L,"testUser3");


        when(userRepository.findAll()).thenReturn(List.of(user_1.toEntity(),user_2.toEntity(),user_3.toEntity()));

        //when
        List<UserInfo> result = userService.getAllUsers();

        //then
        assertEquals(3, result.size());
        assertEquals(user_1.id(), result.get(0).id());
        assertEquals(user_2.id(), result.get(1).id());
        assertEquals(user_3.id(), result.get(2).id());
    }

    @Test
    void 사용자가_존재하지_않을때_예외() {
        //given
        long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getUser(userId);
        });

        //then
        assertEquals("해당 유저를 찾을 수 없습니다.", exception.getMessage());
    }



}
