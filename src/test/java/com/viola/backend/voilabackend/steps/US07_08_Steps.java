package com.viola.backend.voilabackend.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.viola.backend.voilabackend.helper.JsonDataReader;
import com.viola.backend.voilabackend.helper.RequestHelper;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.dto.SocialMediaAccountsDto;
import com.viola.backend.voilabackend.model.dto.UserDto;
import com.viola.backend.voilabackend.model.web.ProfileRequest;
import com.viola.backend.voilabackend.service.UserService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class US07_08_Steps {

    private final String PROFILE_PATH = "/profile";
    private final String PROFILE_FILE = "US07_Req.json";
    private final String SOCIALMEDIAPROFILE_FILE = "US08_Req.json";

    private String jwt;
    private String username;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestHelper requestHelper;

    @Autowired
    private JsonDataReader jsonDataReader;
    
    @Given("Uygulamaya {string} adresi ve {string} şifresi ile giriş yapmış kullanıcı var")
    public void uygulamaya_adresi_ile_giriş_yapmış_kullanıcı_var(String username, String password) throws IOException {
        String jwt = requestHelper.getLoginAndJWT(username, password);
        assertNotNull(jwt);
        assertNotEquals("", jwt);
        this.jwt = jwt;
        this.username = username;
    }
    @Given("Kullanıcı kendi profilini görüntülüyor")
    public void kullanıcı_kendi_profilini_görüntülüyor() {
        System.out.println("Kullanıcı kendi profilini görüntülüyor");
    }
    @When("Kullanıcı değiştirmek istediği alanlara giriş yapıyor")
    public void kullanıcı_değiştirmek_istediği_alanlara_giriş_yapıyor() {
        System.out.println("Kullanıcı alanlara giriş yapıyor");
    }
    @When("kaydet tuşuna bastığında")
    public void kaydet_tuşuna_bastığında() {
        System.out.println("Kullanıcı kaydet tuşuna bastı");
    }
    @Then("Kullanıcı temel bilgileri başarılı bir şekilde güncelleniyor")
    public void kullanıcı_temel_bilgileri_başarılı_bir_şekilde_güncelleniyor() throws IOException{
        UserDto userDto = (UserDto)jsonDataReader.getSingleObjectFromFile(PROFILE_FILE, UserDto.class);
        String url = requestHelper.buildUrl(PROFILE_PATH);
        ProfileRequest profileRequest = new ProfileRequest();
        profileRequest.setPersonal(userDto);
        HttpResponse response = requestHelper.httpPut(profileRequest, url, this.jwt);
        assertEquals(200, response.getStatusLine().getStatusCode());
        HttpEntity entity2 = response.getEntity();
        String responseString = EntityUtils.toString(entity2, "UTF-8");
        Assertions.assertEquals("", responseString);   
    }

    @Then("Kullanıcı güncel bilgilerini görüyor")
    public void kullanıcı_güncel_bilgilerini_görüyor() throws Exception{
        User user = userService.getUserByUsername(this.username);
        UserDto exampleProfile = (UserDto)jsonDataReader.getSingleObjectFromFile(PROFILE_FILE, UserDto.class);
        ModelMapper modelMapper = new ModelMapper();
        ObjectMapper mapper = new ObjectMapper();
        UserDto retrievedUserProfile = modelMapper.map(user, UserDto.class);
        Gson gson = new Gson();
        assertEquals(mapper.readTree(gson.toJson(exampleProfile)), mapper.readTree(gson.toJson(retrievedUserProfile)));
    }

    @When("Kullanıcı temel bilgilerinde güncelleme yapmıyor")
    public void kullanıcı_temel_bilgilerinde_güncelleme_yapmıyor() {
        System.out.println("Kullanıcı temel bilgilerinde güncelleme yapmıyor.");
    }
    @Then("Olumsuz mesaj görüyor")
    public void olumsuz_mesaj_görüyor() throws IOException{
        String url = requestHelper.buildUrl(PROFILE_PATH);
        ProfileRequest profileRequest = new ProfileRequest();
        HttpResponse response = requestHelper.httpPut(profileRequest, url, this.jwt);
        assertEquals(403, response.getStatusLine().getStatusCode());
        HttpEntity entity2 = response.getEntity();
        String responseString = EntityUtils.toString(entity2, "UTF-8");
        Assertions.assertEquals("", responseString);   
    }

    @Then("Kullanıcı sosyal medya hesaplarını başarılı bir şekilde güncelleniyor")
    public void kullanıcı_sosyal_medya_hesaplarını_başarılı_bir_şekilde_güncelleniyor() throws IOException{
        SocialMediaAccountsDto smaDto = (SocialMediaAccountsDto)jsonDataReader.getSingleObjectFromFile(SOCIALMEDIAPROFILE_FILE, SocialMediaAccountsDto.class);
        String url = requestHelper.buildUrl(PROFILE_PATH);
        ProfileRequest profileRequest = new ProfileRequest();
        profileRequest.setSocialMediaAccounts(smaDto);
        HttpResponse response = requestHelper.httpPut(profileRequest, url, this.jwt);
        assertEquals(200, response.getStatusLine().getStatusCode());
        HttpEntity entity2 = response.getEntity();
        String responseString = EntityUtils.toString(entity2, "UTF-8");
        Assertions.assertEquals("", responseString);   
    }
    @Then("Kullanıcı güncel sosyal medya hesap bilgilerini görüyor")
    public void kullanıcı_güncel_sosyal_medya_hesap_bilgilerini_görüyor() throws Exception{
        User user = userService.getUserByUsername(this.username);
        SocialMediaAccountsDto exampleProfile = (SocialMediaAccountsDto)jsonDataReader.getSingleObjectFromFile(SOCIALMEDIAPROFILE_FILE, SocialMediaAccountsDto.class);
        ModelMapper modelMapper = new ModelMapper();
        ObjectMapper mapper = new ObjectMapper();
        SocialMediaAccountsDto retrievedUserProfile = modelMapper.map(user.getSocialMediaAccounts(), SocialMediaAccountsDto.class);
        Gson gson = new Gson();
        assertEquals(mapper.readTree(gson.toJson(exampleProfile)), mapper.readTree(gson.toJson(retrievedUserProfile)));
    }

    @After("@US08Last")
    public void kullanici_sil() {
        User user = userService.getUserByUsername(this.username);
        userService.deleteUser(user);
    }
}
