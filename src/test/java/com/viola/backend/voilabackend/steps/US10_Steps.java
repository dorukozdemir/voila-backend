package com.viola.backend.voilabackend.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import com.viola.backend.voilabackend.exceptions.UserAlreadyExistException;
import com.viola.backend.voilabackend.helper.RequestHelper;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.dto.ProfileDto;
import com.viola.backend.voilabackend.model.dto.UserDto;
import com.viola.backend.voilabackend.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class US10_Steps {
    @Autowired
	private transient UserService userService;

    private final String CONNECTIONS_PATH = "/connections";
    private final String PROFILE_PATH = "/profile";
	private String username, username2, password;
    private String jwt;

    @Autowired
    private RequestHelper requestHelper;
    
    @Given("Kullanıcı1 {string} eposta ve {string} şifresi ile giriş yapmış")
    public void kullanıcı1_eposta_ve_şifresi_ile_giriş_yapmış(String username, String password) throws UserAlreadyExistException, IOException {
        this.username = username;
        this.password = password;
        try {
            userService.createUser(this.username, this.password);
        } catch (UserAlreadyExistException e) {
            System.out.println("Kullanıcı var, olan ile devam ediliyor.");
        }
        String jwt = requestHelper.getLoginAndJWT(this.username, this.password);
        assertNotNull(jwt);
        this.jwt = jwt;
    }
    @When("Kullanıcı bağlantılarını görüntüle ekranına geçiyor")
    public void kullanıcı_bağlantılarını_görüntüle_ekranına_geçiyor() {
        System.out.println("Kullanıcı bağlantılarım ekranına geçti");
    }
    @Then("Hiç bağlantısı olmadığını görüyor")
    public void hiç_bağlantısı_olmadığını_görüyor() throws IOException{
        String url = requestHelper.buildUrl(CONNECTIONS_PATH);
        HttpResponse response = requestHelper.httpGet(url, this.jwt);
        assertEquals(204, response.getStatusLine().getStatusCode());
    }

    @Given("Kullanıcı2yi {string} eposta ve {string} şifresi olan kullanıcının profiline bakıyor")
    public void kullanıcı2yi_eposta_ve_şifresi_olan_kullanıcının_profiline_bakıyor(String username, String password) throws UserAlreadyExistException, IOException {
        this.username2 = username;
        userService.createUser(username, password);
        User user = userService.getUserByUsername(username2);
        assertNotNull(user);
        assertNotNull(user.getProfileToken());
        String url = requestHelper.buildUrl(PROFILE_PATH) + "/" + user.getProfileToken();
        HttpResponse response = requestHelper.httpGet(url, this.jwt);
        assertEquals(200, response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        JsonObject jsonObject = JsonParser.parseString(responseString).getAsJsonObject();
        Assertions.assertTrue(jsonObject.isJsonObject());
        ObjectMapper mapper = new ObjectMapper();
        ProfileDto profileDto = mapper.readValue(responseString, ProfileDto.class);
        assertNotNull(profileDto);
        assertEquals(user.getProfileToken(), profileDto.getPersonal().getProfileToken());

    }
    @Then("Bağlantılar listesinde profiline baktığı kullanıcıyı görüntülüyor")
    public void bağlantılar_listesinde_profiline_baktığı_kullanıcıyı_görüntülüyor() throws IOException {
        System.out.println("Bağlantılarım sayfasına bakacak");
        User thisUser = userService.getUserByUsername(username);
        User user = userService.getUserByUsername(this.username2);
        String url = requestHelper.buildUrl(CONNECTIONS_PATH);
        HttpResponse response = requestHelper.httpGet(url, this.jwt);
        assertEquals(200, response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        UserDto[] userDto = mapper.readValue(responseString, UserDto[].class);
        assertNotNull(userDto);
        assertTrue(userDto.length == 1);
        System.out.println(thisUser.getProfileToken());
        assertEquals(user.getProfileToken(), userDto[0].getProfileToken());
    } 

    @After("@UserConnectionsLast")
    public void kullaniciKaldir() {
        User user1 = userService.getUserByUsername(username);
        if(user1 != null) {
            userService.deleteUser(user1);
        }

        User user2 = userService.getUserByUsername(username2);
        if(user2 != null) {
            userService.deleteUser(user2);
        }
    }
}
