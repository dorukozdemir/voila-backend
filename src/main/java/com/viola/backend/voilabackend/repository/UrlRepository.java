package com.viola.backend.voilabackend.repository;

import java.util.List;

import com.viola.backend.voilabackend.model.domain.Url;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long>{
    /*Company findById(String id);
    List<Company> findByName(String name);
    */
    List<Url> findAll();
    List<Url> findAllByOrderByIdDesc();
    List<Url> findByToken(String token);
}
