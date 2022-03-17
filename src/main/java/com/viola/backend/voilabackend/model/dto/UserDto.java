package com.viola.backend.voilabackend.model.dto;

import java.util.Set;

public class UserDto {
    private String username;
    private String name;
    private String surname;
    private String bio;
    public Set<CompanyInfoDto> companyInfo;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
}

