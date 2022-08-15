package com.viola.backend.voilabackend.model.web;

public class UrlRequest {
    private String count;
    private String companyId;

    public UrlRequest() {

    }
    
    public UrlRequest(String count, String companyId) {
        this.count = count;
        this.companyId = companyId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
