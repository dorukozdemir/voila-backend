package com.viola.backend.voilabackend.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.viola.backend.voilabackend.helper.JsonDataReader;
import com.viola.backend.voilabackend.helper.RequestHelper;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.dto.ProfileDto;
import com.viola.backend.voilabackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class US11_Steps {

    private String jwt, username;

    @Autowired
    private RequestHelper requestHelper;

    @Autowired JsonDataReader jsonDataReader;

    @Autowired
    private UserService userService;

    @Given("Profilini görecek kullanıcı {string} ve {string} ile giriş yapmış")
    public void profilini_görecek_kullanıcı_ve_ile_giriş_yapmış(String username, String password) throws IOException{
        String jwt = requestHelper.getLoginAndJWT(username, password);
        assertNotNull(jwt);
        this.jwt = jwt;
        this.username = username;
    }
    @When("Kullanıcı profilini görüntülüyor")
    public void kullanıcı_profilini_görüntülüyor() {
        System.out.println("Kullanıcı profilini görüntülüyor");
    }
    @Then("Kullanıcının profili ekrana geliyor")
    public void kullanıcının_profili_ekrana_geliyor()  throws IOException{
        String url = requestHelper.buildUrl(RequestHelper.PROFILE_PATH);
        User user = userService.getUserByUsername(this.username);
        HttpResponse response = requestHelper.httpGet(url, this.jwt);
        assertEquals(200, response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        JsonObject jsonObject = JsonParser.parseString(responseString).getAsJsonObject();
        assertTrue(jsonObject.isJsonObject());
        ObjectMapper mapper = new ObjectMapper();
        ProfileDto exampleProfile = (ProfileDto)jsonDataReader.getSingleObjectFromFile("US11_S01_Res.json", ProfileDto.class);
        exampleProfile.getPersonal().setProfileToken(user.getProfileToken());
        Gson gson = new Gson();
        assertEquals(mapper.readTree(gson.toJson(exampleProfile)), mapper.readTree(responseString));
    }
}
