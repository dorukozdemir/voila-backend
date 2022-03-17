package com.viola.backend.voilabackend.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.web.UserRequest;
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

public class US02_Steps {

    private static final String APPLICATION_JSON = "application/json";
    private final String LOGIN_PATH = "/login";

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private String USERNAME = "mete@voila.com";
    private String PASSWORD = "mete123";

    private String username;
    private String password;

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;

    @Autowired
    private UserService userService;
    
    @Given("Uygulamada kayıtlı kullanıcı var")
    public void uygulamada_kullanıcı_yok() {
        if (!userService.isUserExist(USERNAME)) {
            userService.createUser(USERNAME, PASSWORD);
        }
    }

    @Given("Kullanıcı giriş ekranında")
    public void kullanıcı_giris_ekranında() {
        System.out.println("Kullanıcı kayıt ekranında.");
    }

    @Then("Kullanıcı başarılı bir şekilde giriş yapması sağlanır")
    public void kullanıcı_başarılı_bir_şekilde_giriş_yapması_sağlanır() throws IOException {
        int port = webServerAppCtxt.getWebServer().getPort();
        String url = "http://localhost:" + port + LOGIN_PATH;
        HttpPost request = new HttpPost(url);
        Gson gson = new Gson();
        UserRequest userRequest = new UserRequest(USERNAME, PASSWORD);
        String jsonString = gson.toJson(userRequest);
        StringEntity entity = new StringEntity(jsonString);
        request.addHeader("content-type", APPLICATION_JSON);
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);
        assertEquals(200, response.getStatusLine().getStatusCode());
        HttpEntity entity2 = response.getEntity();
        String responseString = EntityUtils.toString(entity2, "UTF-8");
        String[] chunks = responseString.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
        
        Assertions.assertTrue(jsonObject.isJsonObject());
        Assertions.assertTrue(jsonObject.get("sub").getAsString().equals(USERNAME));
    }

    @When("Kullanıcı adı olarak {string} girildiğinde")
    public void kullanıcı_adı_olarak_girildiğinde(String username) {
        this.username = username;
    }
    @When("Şifre olarak {string} girildiğinde")
    public void şifre_olarak_girildiğinde(String password) {
        this.password = password;
    }

    @Then("Bu eposta ve şifre ile kullanıcı bulunmadığına dair bilgi verilir")
    public void bu_eposta_ve_sifre_ile_kullanici_bulunmadigina_dair_bilgi_verilir() throws IOException {
        int port = webServerAppCtxt.getWebServer().getPort();
        String url = "http://localhost:" + port + LOGIN_PATH;
        System.out.println("bağalanacak adres: " + url);
        HttpPost request = new HttpPost(url);
        Gson gson = new Gson();
        UserRequest userRequest = new UserRequest(username, password);
        String jsonString = gson.toJson(userRequest);
        StringEntity entity = new StringEntity(jsonString);
        request.addHeader("content-type", APPLICATION_JSON);
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);
        assertEquals(403, response.getStatusLine().getStatusCode());
        HttpEntity entity2 = response.getEntity();
        String responseString = EntityUtils.toString(entity2, "UTF-8");
        System.out.println(responseString);        
    }

    @After("@US02Last")
    public void kullaniciKaldir() {
        User user = userService.getUserByUsername(USERNAME);
        if(user != null)
            userService.deleteUser(user);
    }

}
