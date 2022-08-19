package com.viola.backend.voilabackend.repository;

import java.util.List;

import com.viola.backend.voilabackend.model.domain.Company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>{
    List<Company> findByName(String name);
    List<Company> findAll();
    List<Company> findByCompanyEmail(String email);
    List<Company> findByNameContaining(String name);
}
