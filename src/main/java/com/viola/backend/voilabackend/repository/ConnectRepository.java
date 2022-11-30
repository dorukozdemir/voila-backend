package com.viola.backend.voilabackend.repository;

import java.util.List;

import com.viola.backend.voilabackend.model.domain.Connect;
import com.viola.backend.voilabackend.model.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectRepository extends JpaRepository<Connect, Long>{
    List<Connect> findByUser(User user);
    List<Connect> findAll();
    List<Connect> findByUserAndId(User user, Long id);
}
