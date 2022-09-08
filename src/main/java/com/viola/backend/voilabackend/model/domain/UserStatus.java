package com.viola.backend.voilabackend.model.domain;

public enum UserStatus {
    ACTIVE("ACTIVE"),
    PASSIVE("PASSIVE"),
    SETUP("SETUP");

    private String label;

    private UserStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
