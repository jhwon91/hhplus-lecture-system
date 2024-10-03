package com.hhplus.clean.infrastructure.persistence.user;
import com.hhplus.clean.infrastructure.entity.UserEntity;
import com.hhplus.clean.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private  final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl (JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findById(long id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return jpaUserRepository.findAll();
    }
}
