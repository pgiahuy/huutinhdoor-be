package com.pgh.huutinhdoor.repository;

import com.pgh.huutinhdoor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}
