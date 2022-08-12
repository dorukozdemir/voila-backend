package com.viola.backend.voilabackend.model.dto;

import com.viola.backend.voilabackend.model.domain.Company;

public class CompanyListItem {
    private Long id;
    private String name; 
    public CompanyListItem(Company company) {
        this.id = company.getId();
        this.name = company.getName();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
