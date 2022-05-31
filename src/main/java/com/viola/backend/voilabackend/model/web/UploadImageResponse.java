package com.viola.backend.voilabackend.model.web;

public class UploadImageResponse {
    private boolean status;
    private String path;
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
