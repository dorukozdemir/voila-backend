package com.viola.backend.voilabackend.model.dto;

import com.viola.backend.voilabackend.model.domain.Connect;

public class ConnectDto {
    private String nameSurname;
    private String email;
    private String note;
    private String phone;
    private boolean agreementChecked;

    public ConnectDto() {

    }

    public ConnectDto(String nameSurname, String email, String phone, String note, boolean agreementChecked) {
        this.nameSurname = nameSurname;
        this.email = email;
        this.phone = phone;
        this.note = note;
        this.agreementChecked = agreementChecked;
    }
    
    public ConnectDto(Connect connect) {
        this.nameSurname = connect.getNameSurname();
        this.email = connect.getEmail();
        this.phone = connect.getPhone();
        this.note = connect.getNote();
        this.agreementChecked = connect.isAgreementChecked();
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
}

