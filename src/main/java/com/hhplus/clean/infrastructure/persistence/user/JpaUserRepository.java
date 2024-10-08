package com.hhplus.clean.infrastructure.persistence.user;

import com.hhplus.clean.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
