package com.viola.backend.voilabackend.model;

public enum ContactType {
    WHATSAPP("whatsapp"),
    PHONE("phone"),
    EMAIL("email");

    private String text;

    ContactType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
