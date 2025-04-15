package com.example.auth_service.repository;

import com.example.auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
