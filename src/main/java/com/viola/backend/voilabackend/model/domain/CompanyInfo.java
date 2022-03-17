package com.viola.backend.voilabackend.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "v_company_info_t")
public class CompanyInfo {
    
    private Long id;
    private User user;
    private String name;
    private String address;
    private String taxNo;
    private String taxBody;

    public CompanyInfo() {

    }

    public CompanyInfo(String name, String address, String taxNo, String taxBody) {
        this.name = name;
        this.address = address;
        this.taxNo = taxNo;
        this.taxBody = taxBody;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getTaxBody() {
        return taxBody;
    }

    public void setTaxBody(String taxBody) {
        this.taxBody = taxBody;
    }
    
}
