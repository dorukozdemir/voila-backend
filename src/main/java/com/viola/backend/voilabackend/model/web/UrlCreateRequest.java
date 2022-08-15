package com.viola.backend.voilabackend.model.web;

import java.util.List;

public class UrlCreateRequest {
    List<UrlRequest> urls;

    public UrlCreateRequest() {

    }
    
    public UrlCreateRequest(List<UrlRequest> urls) {
        this.urls = urls;
    }

    public List<UrlRequest> getUrls() {
        return urls;
    }

    public void setUrls(List<UrlRequest> urls) {
        this.urls = urls;
    }
}
