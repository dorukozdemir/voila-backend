package com.viola.backend.voilabackend.repository;

import java.util.List;

import com.viola.backend.voilabackend.model.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByUsername(String username);
    List<User> findByResetPasswordToken(String resetToken);
    List<User> findByProfileToken(String profileToken);
    List<User> findAll();
}
