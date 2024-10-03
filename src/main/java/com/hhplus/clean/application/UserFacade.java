package com.hhplus.clean.application;

import com.hhplus.clean.domain.user.UserCommand;
import com.hhplus.clean.domain.user.UserInfo;
import com.hhplus.clean.domain.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFacade {
    private final UserService userService;

    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    public UserInfo saveUser(UserCommand user) {
        return userService.saveUser(user);
    }

    public UserInfo getUser(long userId) {
        return userService.getUser(userId);
    }

    public List<UserInfo> getAllUsers() {
        return userService.getAllUsers();
    }
}
