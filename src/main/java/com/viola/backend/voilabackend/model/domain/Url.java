package com.viola.backend.voilabackend.model.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "v_url_t")
public class Url  {
    private Long id;
    private String token = UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(0, 10);;
    private Date created = new Date();
    private Company company;
    private Admin admin;
    private String note;
    
    public Url() {

    }

    public Url(String token, Admin admin, Company company, String note ) {
        this.token = token;
        this.admin = admin;
        this.company = company;
        this.note = note;
    }

    @Id
    /*@SequenceGenerator(name="cardvisits_id_seq",
                   sequenceName="cardvisits_id_seq",
                   allocationSize=1) */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="created_at")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @ManyToOne
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @ManyToOne
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    
}
