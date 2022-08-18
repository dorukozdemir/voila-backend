package com.viola.backend.voilabackend.model.dto.admin;


import com.google.gson.Gson;
import com.viola.backend.voilabackend.model.domain.User;


public class EditProfileDto {
    private String name;
    private String surname;
    private String profileToken;
    private String profilePhoto;
    private String note;
    private String email;

    public EditProfileDto() {

    }

    public EditProfileDto(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.profileToken = user.getProfileToken();
        this.profilePhoto = user.getProfilePhoto();
        this.note = user.getNote();
    }

    public String jsonString() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        return jsonString;
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

    public String getProfileToken() {
        return profileToken;
    }

    public void setProfileToken(String profileToken) {
        this.profileToken = profileToken;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
