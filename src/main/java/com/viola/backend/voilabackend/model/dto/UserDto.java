package com.viola.backend.voilabackend.model.dto;

public class UserDto {
    private String name;
    private String surname;
    private String bio;

    public UserDto() {

    }

    public UserDto(String name, String surname, String bio) {
        this.name = name;
        this.surname = surname;
        this.bio = bio;
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

