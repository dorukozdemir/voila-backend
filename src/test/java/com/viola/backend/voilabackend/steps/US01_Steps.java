package com.viola.backend.voilabackend.steps;

import java.io.IOException;
import java.util.Base64;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.viola.backend.voilabackend.helper.RequestHelper;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.web.UserRequest;
import com.viola.backend.voilabackend.service.UserService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class US01_Steps {
    private final String REGISTER_PATH = "/register";

    private String username;
    private String password;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestHelper requestHelper;

    @Before
    public void sunucu_ayarlamalari() {

    }

    @Given("Uygulamada kullanıcı yok")
    public void uygulamada_kullanıcı_yok() {
        System.out.println("Kullanıcı yok.");
    }
    @Given("Kullanıcı kayıt ekranında")
    public void kullanıcı_kayıt_ekranında() {
        System.out.println("Kullanıcı kayıt ekranında.");
    }
    @When("Kullanıcı adı {string} girildiğinde")
    public void kullanıcı_adı_girildiğinde(String username) {
        this.username = username;
        System.out.println("Kullanıcı adı  girildi: " + username);
    }
    @When("Şifre {string} girildiğinde")
    public void şifre_girildiğinde(String password) {
        this.password = password;
        System.out.println("Kullanıcı şifresi girildi: " + password);
    }
    @Then("Kullanıcı başarılı bir şekilde kaydolduğuna dair mesaj görüntülenir ve kullanıcı girişi sağlanır")
    public void kullanıcı_başarılı_bir_şekilde_kaydolduğuna_dair_mesaj_görüntülenir_ve_kullanıcı_girişi_sağlanır() throws IOException {
        UserRequest userRequest = new UserRequest(username, password);
        String url = requestHelper.buildUrl(REGISTER_PATH);
        HttpResponse response = requestHelper.httpPost(userRequest, url);
        Assertions.assertEquals(200, response.getStatusLine().getStatusCode());
        HttpEntity entity2 = response.getEntity();
        String responseString = EntityUtils.toString(entity2, "UTF-8");
        String[] chunks = responseString.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
        Assertions.assertTrue(jsonObject.isJsonObject());
        Assertions.assertTrue(jsonObject.get("sub").getAsString().equals(username));
    }

    @Then("Aynı eposta ile kaydolunduğuna dair cevap döner")
    public void aynı_eposta_ile_kaydolunduğuna_dair_cevap_döner() throws IOException{
        UserRequest userRequest = new UserRequest(username, password);
        String url = requestHelper.buildUrl(REGISTER_PATH);
        HttpResponse response = requestHelper.httpPost(userRequest, url);
        Assertions.assertEquals(409, response.getStatusLine().getStatusCode());
        HttpEntity entity2 = response.getEntity();
        String responseString = EntityUtils.toString(entity2, "UTF-8");
        Assertions.assertEquals("", responseString);       
    }

    @After("@US01Last")
    public void kullaniciKaldir() {
        User user = userService.getUserByUsername(username);
        userService.deleteUser(user);
    }
}
