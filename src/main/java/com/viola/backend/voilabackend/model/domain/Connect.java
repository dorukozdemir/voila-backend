package com.viola.backend.voilabackend.model.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.viola.backend.voilabackend.model.dto.ConnectDto;

@Entity
@Table(name = "v_connect_t")
public class Connect {

    private Long id;
    private String email;
    private String nameSurname;
    private String note;
    private String phone;
    private User user;
    private boolean agreementChecked;
    private Date created = new Date();

    public Connect() {

    }

    public Connect(String email, String nameSurname, String note, User user, String phone, boolean agreementChecked) {
        this.email = email;
        this.nameSurname = nameSurname;
        this.note = note;
        this.user = user;
        this.phone = phone;
        this.agreementChecked = agreementChecked;
    }

    public Connect(ConnectDto connectDto) {
        this.email = connectDto.getEmail();
        this.nameSurname = connectDto.getNameSurname();
        this.note = connectDto.getNote();
        this.phone = connectDto.getPhone();
        this.agreementChecked = connectDto.isAgreementChecked();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="email", unique=true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }
    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name="created_at")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }  

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAgreementChecked() {
        return agreementChecked;
    }

    public void setAgreementChecked(boolean agreementChecked) {
        this.agreementChecked = agreementChecked;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof User) {
            if (((User) obj).getId().equals(getId())) {
                return true;
            }
        }

        return false;
    }
}