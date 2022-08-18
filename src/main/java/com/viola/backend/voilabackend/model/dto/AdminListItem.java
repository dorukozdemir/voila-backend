package com.viola.backend.voilabackend.model.dto;

import com.viola.backend.voilabackend.model.domain.Admin;

public class AdminListItem {
    private String fullname;
    private String name;
    private String surname;
    private String company;
    private String email;
    private String type;

    public AdminListItem (Admin admin) {
        this.fullname = admin.getFullName();
        this.name = admin.getName();
        this.surname = admin.getSurname();
        this.company = "Voila";
        this.email = admin.getUsername();
        this.type = admin.getRole().toString();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
