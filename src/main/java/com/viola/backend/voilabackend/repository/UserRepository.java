package com.viola.backend.voilabackend.repository;

import java.util.List;
import com.viola.backend.voilabackend.model.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User>{
    List<User> findByUsername(String username);
    List<User> findByResetPasswordToken(String resetToken);
    List<User> findByProfileToken(String profileToken);
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    List<User> findFirst10ByOrderByProfileVisitsDesc();
    Page<User> findByUsernameContainingAndProfileTokenContainingAndNameContainingAndSurnameContaining( String username, String profileToken, String name, String surname, Pageable pageable);
    Page<User> findByUsernameContainingOrProfileTokenContainingOrNameContainingOrSurnameContaining(String username,
            String token, String name, String surname, Pageable pagination);
    Page<User> findAll(Specification<User> specification, Pageable pagination);
}
