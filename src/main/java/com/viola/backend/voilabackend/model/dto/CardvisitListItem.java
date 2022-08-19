package com.viola.backend.voilabackend.model.dto;

import com.viola.backend.voilabackend.model.domain.User;

public class CardvisitListItem {
    private String name;
    private String surname;
    private String company;
    private String email;
    private int profileVisits;
    private String profileToken;

    public CardvisitListItem (User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.company = "Voila";
        this.email = user.getUsername();
        this.profileVisits = user.getProfileVisits();
        this.profileToken = user.getProfileToken();
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
    public int getProfileVisits() {
        return profileVisits;
    }
    public void setProfileVisits(int profileVisits) {
        this.profileVisits = profileVisits;
    }
    public String getProfileToken() {
        return profileToken;
    }
    public void setProfileToken(String profileToken) {
        this.profileToken = profileToken;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
