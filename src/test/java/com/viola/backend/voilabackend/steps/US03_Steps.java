package com.viola.backend.voilabackend.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.viola.backend.voilabackend.model.User;
import com.viola.backend.voilabackend.model.UserRequest;
import com.viola.backend.voilabackend.service.UserService;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.apache.http.HttpEntity;

public class US03_Steps {

    private static final String APPLICATION_JSON = "application/json";
    private final String FORGOT_PATH = "/forgot";

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private String USERNAME = "mete@voila.com";
    private String PASSWORD = "mete123";

    private String username;
    private String password;

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;

    @Autowired
    private UserService userService;
    
    @Given("Kullanıcı şifre unuttum ekranında")
    public void kullanıcı_şifre_unuttum_ekranında() {
        System.out.println("Kullanıcı şifre unuttum ekranında");
    }
    @When("Kullanıcı adı için {string} girildiğinde")
    public void kullanıcı_adı_için_girildiğinde(String username) {
        this.username = username;
    }
    @When("Şifremi yenile butonuna tıklandığında")
    public void şifremi_yenile_butonuna_tıklandığında() {
        System.out.println("Şifremi yenile butonu tıklandı");
    }
    
    @Then("Kullanıcıya e-posta gidiyor")
    public void kullanıcıya_e_posta_gidiyor() throws IOException {
        int port = webServerAppCtxt.getWebServer().getPort();
        String url = "http://localhost:" + port + FORGOT_PATH;
        HttpPost request = new HttpPost(url);
        Gson gson = new Gson();
        UserRequest userRequest = new UserRequest(USERNAME);
        String jsonString = gson.toJson(userRequest);
        StringEntity entity = new StringEntity(jsonString);
        request.addHeader("content-type", APPLICATION_JSON);
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);
        assertEquals(200, response.getStatusLine().getStatusCode());
        User user = userService.getUserByUsername(USERNAME);
        assertTrue(userService.isForgotTokenValid(user));
    }
    @Then("Kullanıcıya genel bir bu eposta ile bağlantılı kullanıcı varsa şifre sıfırlama epostası gönderilmiştir mesajı")
    public void kullanıcıya_genel_bir_bu_eposta_ile_bağlantılı_kullanıcı_varsa_şifre_sıfırlama_epostası_gönderilmiştir_mesajı() throws IOException {
        int port = webServerAppCtxt.getWebServer().getPort();
        String url = "http://localhost:" + port + FORGOT_PATH;
        HttpPost request = new HttpPost(url);
        Gson gson = new Gson();
        UserRequest userRequest = new UserRequest(USERNAME);
        String jsonString = gson.toJson(userRequest);
        StringEntity entity = new StringEntity(jsonString);
        request.addHeader("content-type", APPLICATION_JSON);
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @After("@US03Last")
    public void kullaniciKaldir() {
        User user = userService.getUserByUsername(USERNAME);
        if(user != null)
            userService.deleteUser(user);
    }

}
