package com.viola.backend.voilabackend.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.nullable;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.viola.backend.voilabackend.exceptions.UserAlreadyExistException;
import com.viola.backend.voilabackend.helper.JsonDataReader;
import com.viola.backend.voilabackend.helper.RequestHelper;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.service.UserService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

public class US05_Steps {

    private final String PROFILE_PATH = "/profile";

    private String username;
    private String jwt;
    private String password;
    private String profileToken;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestHelper requestHelper;

    @Autowired
    private JsonDataReader jsonDataReader;
    
    @Given("Kullanan kullanıcı {string} {string} ile kayıtlı")
    public void kullanan_kullanıcı_ile_kayıtlı(String username, String password) {
        this.username = username;
        this.password = password;
        try {
            userService.createUser(this.username, this.password);
        } catch (UserAlreadyExistException e) {
            System.out.println("Kullanıcı var, olan ile devam ediliyor.");
        }
    }
    @Given("Kullanan kullanıcı giriş yapmış durumda")
    public void kullanan_kullanıcı_giriş_yapmış_durumda() throws IOException{
        String jwt = requestHelper.getLoginAndJWT(this.username, this.password);
        assertNotNull(jwt);
        this.jwt = jwt;
    }
    @Given("Kullanıcı içeri aktarma ekranında")
    public void kullanıcı_içeri_aktarma_ekranında() {
        System.out.println("Kullanıcı içeri aktarma ekranında");
    }
    @Given("Profilini görüntüleyen kullanıcı {string} tokenını göstermiş")
    public void profilini_görüntüleyen_kullanıcı_tokenını_göstermiş(String username) {
        User user = userService.getUserByUsername(username);
        assertTrue(user != null && user.getProfileToken() != null);
        this.profileToken = user.getProfileToken();
    }
    @When("Kullanıcı kartı okutuyor")
    public void kullanıcı_kartı_okutuyor() {
        User user = userService.getByProfileToken(this.profileToken);
        assertNotNull(user);
    }
    @Then("Kartta tanımlı kullanıcının profil bilgileri ekrana geliyor")
    public void kartta_tanımlı_kullanıcının_profil_bilgileri_ekrana_geliyor() throws IOException {
        String url = requestHelper.buildUrl(PROFILE_PATH) + "/" + this.profileToken;
        HttpResponse response = requestHelper.httpGet(url, this.jwt);
        assertEquals(200, response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        JsonObject jsonObject = JsonParser.parseString(responseString).getAsJsonObject();
        Assertions.assertTrue(jsonObject.isJsonObject());
        ObjectMapper mapper = new ObjectMapper();
        String testJsonString = jsonDataReader.getJsonStringfromFile("US05_S01_Res.json");
        assertEquals(mapper.readTree(testJsonString), mapper.readTree(responseString));
    }
    @When("Kullanıcı kartı okutuyor ve {string} idli profili görüntülemek istiyor")
    public void kullanıcı_kartı_okutuyor_ve_idli_profili_görüntülemek_istiyor(String profileToken) {
        this.profileToken = profileToken;
    }
    @Then("Kullanıcının olmadığına dair ekran geliyor")
    public void kullanıcının_olmadığına_dair_ekran_geliyor() throws IOException {
        String url = requestHelper.buildUrl(PROFILE_PATH) + "/" + this.profileToken;
        HttpResponse response = requestHelper.httpGet(url, this.jwt);
        assertEquals(404, response.getStatusLine().getStatusCode());
    }

    @After("@US05Last")
    public void kullanici_temizle() {
        User user = userService.getUserByUsername(this.username);
        userService.deleteUser(user);
        if(this.profileToken!= null) {
            User otherUser = userService.getByProfileToken(profileToken);
            if (otherUser != null) {
                userService.deleteUser(otherUser);
            }
        }
    }
}
