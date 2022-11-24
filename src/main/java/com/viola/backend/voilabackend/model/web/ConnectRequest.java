package com.viola.backend.voilabackend.model.web;

import com.viola.backend.voilabackend.model.dto.ConnectDto;

public class ConnectRequest extends ConnectDto implements Request{
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
