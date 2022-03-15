package com.viola.backend.voilabackend.helper;

import java.io.IOException;

import com.google.gson.Gson;
import com.viola.backend.voilabackend.model.Request;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Service;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

@Service("postRequestHelper")
public class PostRequestHelper {

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;
    
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final String APPLICATION_JSON = "application/json";

    private final String DOMAIN = "http://localhost:";

    public HttpResponse httpPost(Request requestObject, String url) throws IOException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(requestObject);
        StringEntity entity = new StringEntity(jsonString);
        HttpPost request = new HttpPost(url);
        request.addHeader("content-type", APPLICATION_JSON);
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);
        return response;
    }

    public String buildUrl(String PATH) {
        int port = webServerAppCtxt.getWebServer().getPort();
        String url = DOMAIN + port + PATH;
        return url;
    }
}
