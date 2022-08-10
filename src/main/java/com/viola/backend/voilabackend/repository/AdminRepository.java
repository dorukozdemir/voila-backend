package com.viola.backend.voilabackend.repository;

import java.util.List;

import com.viola.backend.voilabackend.model.domain.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>{
    List<Admin> findByUsername(String username);
    List<Admin> findByResetPasswordToken(String resetToken);
    List<Admin> findAll();
}
