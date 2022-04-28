package com.viola.backend.voilabackend.repository;

import java.util.List;

import com.viola.backend.voilabackend.model.domain.Connection;
import com.viola.backend.voilabackend.model.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long>{
    List<Connection> findByUser1(User user);
    List<Connection> findByUser2(User user);
    List<Connection> findAll();
    Connection findByUser1AndUser2(User user1, User user2);
}
