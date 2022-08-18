package com.viola.backend.voilabackend.repository;

import java.util.List;

import com.viola.backend.voilabackend.model.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{
    List<User> findByUsername(String username);
    List<User> findByResetPasswordToken(String resetToken);
    List<User> findByProfileToken(String profileToken);
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    List<User> findFirst10ByOrderByProfileVisitsDesc();
}
