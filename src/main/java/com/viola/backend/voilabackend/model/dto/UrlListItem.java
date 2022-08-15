package com.viola.backend.voilabackend.model.dto;

import com.viola.backend.voilabackend.model.domain.Company;
import com.viola.backend.voilabackend.model.domain.Url;

public class UrlListItem {
    private String token;
    private Company company;
    private String admin;
    private String note;
    private String createdAt;

    public UrlListItem(Url url) {
        this.token = url.getToken();
        this.company = url.getCompany();
        this.admin = url.getAdmin().getFullName();
        this.note = url.getNote();
        this.createdAt = url.getCreated().toString();
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public String getAdmin() {
        return admin;
    }
    public void setAdmin(String admin) {
        this.admin = admin;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
}
