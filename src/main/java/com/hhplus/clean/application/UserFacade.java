package com.hhplus.clean.application;

import com.hhplus.clean.domain.user.UserEntity;
import com.hhplus.clean.domain.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFacade {
    private final UserService userService;

    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    public UserEntity saveUser(UserEntity user) {
        return userService.saveUser(user);
    }

    public UserEntity getUser(long userId) {
        return userService.getUser(userId);
    }

    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
}
