package com.viola.backend.voilabackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "v_contact_info_t")
public class ContactInfo {
    
    private Long id;
    private User user;
    private ContactType contactType;
    private String extension;
    private String value;

    public ContactInfo() {

    }

    public ContactInfo(ContactType contactType, String extension, String value) {
        this.contactType = contactType;
        this.extension = extension;
        this.value = value;
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
    public ContactType getContactType() {
        return contactType;
    }
    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }   
}
