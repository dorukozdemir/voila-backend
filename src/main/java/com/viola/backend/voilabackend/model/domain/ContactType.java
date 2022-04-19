package com.viola.backend.voilabackend.model.domain;

public enum ContactType {
    WHATSAPP("whatsapp"),
    PHONE("phone"),
    EMAIL("email");

    private String text;

    ContactType(String text) {
        this.text = text;
    }

    public static ContactType toContactType(String text) {
        switch(text) {
            case "whatsapp":
                return WHATSAPP;
            case "phone":
                return PHONE;
            case "email":
                return EMAIL;
            default:
                return PHONE;     
        }
    }

    @Override
    public String toString() {
        return this.text;
    }
}
