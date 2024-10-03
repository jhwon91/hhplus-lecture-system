package com.hhplus.clean.domain;

import com.hhplus.clean.infrastructure.entity.UserEntity;
import com.hhplus.clean.domain.user.UserRepository;
import com.hhplus.clean.domain.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    //given
    //when
    //then
    @Test
    void 사용자_생성(){
        //given
        UserEntity user = new UserEntity();
        user.setUsername("testUser");

        UserEntity saveUser = new UserEntity();
        saveUser.setId(1L);
        saveUser.setUsername("testUser");

        when(userRepository.save(user)).thenReturn(saveUser);

        //when
        UserEntity result = userService.saveUser(user);

        //then
        assertEquals(1L, result.getId());
        assertEquals("testUser", result.getUsername());
    }

    @Test
    void 단일_사용자_조회() {
        //given
        long userId = 1L;
        UserEntity user_1 = new UserEntity();
        user_1.setId(userId);
        user_1.setUsername("testUser");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user_1));

        //when
        UserEntity result = userService.getUser(userId);

        //then
        assertEquals(userId, result.getId());
        assertEquals("testUser", result.getUsername());
    }

    @Test
    void 사용자_목록_조회() {
        //given
        UserEntity user_1 = new UserEntity();
        user_1.setId(1L);
        user_1.setUsername("testUser");

        UserEntity user_2 = new UserEntity();
        user_2.setId(2L);
        user_2.setUsername("testUser2");

        UserEntity user_3 = new UserEntity();
        user_3.setId(3L);
        user_3.setUsername("testUser3");

        when(userRepository.findAll()).thenReturn(List.of(user_1,user_2,user_3));

        //when
        List<UserEntity> result = userService.getAllUsers();

        //then
        assertEquals(3, result.size());
        assertEquals(user_1, result.get(0));
        assertEquals(user_2, result.get(1));
        assertEquals(user_3, result.get(2));
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
