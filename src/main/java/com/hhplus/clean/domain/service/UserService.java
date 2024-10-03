package com.hhplus.clean.domain.service;

import com.hhplus.clean.domain.Repository.UserRepository;
import com.hhplus.clean.domain.entity.UserEntity;
import com.hhplus.clean.domain.dto.UserCommand;
import com.hhplus.clean.domain.dto.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfo saveUser(UserCommand user) {
        return  UserInfo.from(userRepository.save(user.toEntity()));
    }

    public UserInfo getUser(long userId) {
        return UserInfo.from(
                userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."))
        );
    }

    public List<UserInfo> getAllUsers() {
        List<UserEntity> user = userRepository.findAll();
        return userRepository.findAll().stream()
                .map(UserInfo::from)
                .collect(Collectors.toList());
    }
}
