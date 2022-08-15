package com.viola.backend.voilabackend.model.web;

public class CompanyRequest {
    private String companyEmail;
    private String name;
    private String phone;
    private String authorityEmail;
    private String authorityName;
    public CompanyRequest() {

    }

    public CompanyRequest(String name, String companyEmail, String phone, String authorityEmail, String authorityName) {
        this.companyEmail = companyEmail;
        this.name = name;
        this.phone = phone;
        this.authorityEmail = authorityEmail;
        this.authorityName = authorityName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
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
}
