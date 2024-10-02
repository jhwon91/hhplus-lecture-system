package com.hhplus.clean.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity user);
    Optional<UserEntity> findById(long id);
    List<UserEntity> findAll();
}


