package com.viola.backend.voilabackend.model.dto;

import com.viola.backend.voilabackend.model.domain.User;

public class UserDto {
    private String name;
    private String surname;
    private String bio;
    private String profileToken;
    private int profileVisits;

    public UserDto() {

    }

    public UserDto(String name, String surname, String bio) {
        this.name = name;
        this.surname = surname;
        this.bio = bio;
    }
    
    public UserDto(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.bio = user.getBio();
        this.profileToken = user.getProfileToken();
        this.profileVisits = user.getProfileVisits();
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

    public String getProfileToken() {
        return profileToken;
    }

    public void setProfileToken(String profileToken) {
        this.profileToken = profileToken;
    }

    public int getProfileVisits() {
        return profileVisits;
    }

    public void setProfileVisits(int profileVisits) {
        this.profileVisits = profileVisits;
    }
}

