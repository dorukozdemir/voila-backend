package com.viola.backend.voilabackend.model.dto;

import com.viola.backend.voilabackend.model.domain.Company;

public class CompanyListItem {
    private Long id;
    private String name; 
    private String phone;
    private String companyEmail;
    private String authorityEmail;
    private String authorityName;
    private String adminName;
    private String logo;
    private int userCount;
    
    public CompanyListItem(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.phone = company.getPhone();
        this.companyEmail = company.getCompanyEmail();
        this.authorityEmail = company.getAuthorityEmail();
        this.authorityName = company.getAuthorityName();
        this.adminName = company.getAdmin().getFullName();
        this.logo = company.getLogo();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCompanyEmail() {
        return companyEmail;
    }
    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
    public String getAuthorityEmail() {
        return authorityEmail;
    }
    public void setAuthorityEmail(String authorityEmail) {
        this.authorityEmail = authorityEmail;
    }
    public String getAuthorityName() {
        return authorityName;
    }
    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public int getUserCount() {
        return userCount;
    }
    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }    
}
