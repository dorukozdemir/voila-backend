package com.viola.backend.voilabackend.model.web;

public class ResetRequest implements Request{
    private String token;
    private String password;

    public ResetRequest() {

    }

    public ResetRequest(String token, String password) {
        this.token = token;
        this.password = password;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
