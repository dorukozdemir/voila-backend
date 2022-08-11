package com.viola.backend.voilabackend.model.dto;

import com.viola.backend.voilabackend.model.domain.Admin;

public class AdminListItem {
    private String name;
    private String company;
    private String email;
    private String type;

    public AdminListItem (Admin admin) {
        this.name = admin.getFullName();
        this.company = "Voila";
        this.email = admin.getUsername();
        this.type = admin.getRole().toString();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
