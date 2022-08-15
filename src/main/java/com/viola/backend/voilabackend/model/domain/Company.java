package com.viola.backend.voilabackend.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "v_company_t")
public class Company {
    
    private Long id;
    private String name;
    private String phone;
    private String companyEmail;
    private String authorityEmail;
    private String authorityName;
    private Admin admin;

    public Company() {

    }

    public Company(String name) {
        this.name = name;
    }

    public Company(String name, String phone, String companyEmail, String authorityEmail, String authorityName, Admin admin) {
        this.name = name;
        this.phone = phone;
        this.companyEmail = companyEmail;
        this.authorityEmail = authorityEmail;
        this.authorityName = authorityName;
        this.admin = admin;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    
}
