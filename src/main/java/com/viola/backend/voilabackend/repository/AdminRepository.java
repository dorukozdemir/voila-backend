package com.viola.backend.voilabackend.repository;

import java.util.List;

import com.viola.backend.voilabackend.model.domain.Admin;
import com.viola.backend.voilabackend.model.domain.Company;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin>{
    List<Admin> findByUsername(String username);
    List<Admin> findByResetPasswordToken(String resetToken);
    List<Admin> findAll();
    Specification<Admin> findByNameContainsOrSurnameContainsOrUsernameContainsOrCompanyIn(String search, String search2, String search3, List<Company> companies);
    List<Admin> findByCompanyAndNameContainsOrSurnameContainsOrUsernameContainsOrCompanyIn(Company company,
            String search, String search2, String search3, List<Company> companies);
    List<Admin> findAll(Specification<Admin> specification);
    Specification<Admin> findByCompany(Company company);
}
