package com.viola.backend.voilabackend.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.viola.backend.voilabackend.helper.DateStringHelper;
import com.viola.backend.voilabackend.model.domain.Company;
import com.viola.backend.voilabackend.model.domain.Url;

public class UrlListItem {
    private String token;
    private Company company;
    private String admin;
    private String note;
    private String createdAt;
    private boolean setup;

    public UrlListItem(Url url) {
        DateStringHelper dsh = new DateStringHelper();
        String inWords = dsh.dateDifferenceInWords(url.getCreated(), new Date());
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm");  
        String createdAtString = formatter.format(url.getCreated()); 

        this.token = url.getToken();
        this.company = url.getCompany();
        this.admin = url.getAdmin().getFullName();
        this.note = url.getNote();
        this.createdAt = createdAtString + " " + inWords;
    }
    public UrlListItem(Url url, boolean setup) {
        DateStringHelper dsh = new DateStringHelper();
        String inWords = dsh.dateDifferenceInWords(url.getCreated(), new Date());
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm");  
        String createdAtString = formatter.format(url.getCreated()); 

        this.token = url.getToken();
        this.company = url.getCompany();
        this.admin = url.getAdmin().getFullName();
        this.note = url.getNote();
        this.createdAt = createdAtString + " " + inWords;
        this.setup = setup;
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
    public boolean isSetup() {
        return setup;
    }
    public void setSetup(boolean setup) {
        this.setup = setup;
    }
    
}
