package com.viola.backend.voilabackend.helper;

import java.io.IOException;

import com.google.gson.Gson;
import com.viola.backend.voilabackend.model.web.Request;
import com.viola.backend.voilabackend.model.web.UserRequest;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Service;

@Service("requestHelper")
public class RequestHelper {

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;
    
    public static final String PROFILE_PATH = "/profile";
    
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final String APPLICATION_JSON = "application/json";

    private final String DOMAIN = "http://localhost:";
    private final String LOGIN_PATH = "/login";

    public HttpResponse httpPost(Request requestObject, String url) throws IOException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(requestObject);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
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

    public String getLoginAndJWT(String username, String password) throws IOException{
        String url = buildUrl(LOGIN_PATH);
        UserRequest userRequest = new UserRequest(username, password);
        HttpResponse response = httpPost(userRequest, url);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        return responseString;
    }

    public HttpResponse httpGet(String url, String jwt) throws IOException{
        HttpGet request = new HttpGet(url);
        request.addHeader("content-type", APPLICATION_JSON);
        request.addHeader("Authorization", "Bearer " + jwt);
        HttpResponse response = httpClient.execute(request);
        return response;
    }

    public HttpResponse httpPut(Request requestObject, String url, String jwt) throws IOException{
        Gson gson = new Gson();
        String jsonString = gson.toJson(requestObject);
        HttpPut request = new HttpPut(url);
        request.addHeader("content-type", APPLICATION_JSON);
        request.addHeader("Authorization", "Bearer " + jwt);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);
        return response;
    }
}
