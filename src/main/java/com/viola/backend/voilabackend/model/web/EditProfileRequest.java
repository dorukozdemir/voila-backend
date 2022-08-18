package com.viola.backend.voilabackend.model.web;

import com.viola.backend.voilabackend.model.dto.admin.EditProfileDto;

public class EditProfileRequest extends EditProfileDto implements Request{
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
