package com.viola.backend.voilabackend.model.web;

import java.util.ArrayList;
import java.util.List;

import com.viola.backend.voilabackend.model.domain.Company;

public class UserSearch {
    private String name ="";
    private String surname ="";
    private String username = "";
    private String email = "";
    private String url = "";
    private List<Company> companies = new ArrayList<Company>();
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public List<Company> getCompanies() {
        return companies;
    }
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
