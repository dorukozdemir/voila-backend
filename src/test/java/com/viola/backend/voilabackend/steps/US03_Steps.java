package com.viola.backend.voilabackend.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Date;
import com.viola.backend.voilabackend.helper.PostRequestHelper;
import com.viola.backend.voilabackend.model.ResetRequest;
import com.viola.backend.voilabackend.model.User;
import com.viola.backend.voilabackend.model.UserRequest;
import com.viola.backend.voilabackend.service.UserService;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.HttpEntity;

public class US03_Steps {

    private final String FORGOT_PATH = "/forgot";
    private final String RESET_PATH = "/reset";

    private String username;
    private String token;
    private String password;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRequestHelper postRequestHelper;
    
    @Given("Kullanıcı {string} adresi ile işlem yapmak istiyor")
    public void kullanıcı_adresi_ile_işlem_yapmak_istiyor(String username) {
        this.username = username;
    }

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
        String url = postRequestHelper.buildUrl(FORGOT_PATH);
        UserRequest userRequest = new UserRequest(username);
        HttpResponse response = postRequestHelper.httpPost(userRequest, url);
        assertEquals(200, response.getStatusLine().getStatusCode());
        User user = userService.getUserByUsername(username);
        assertTrue(userService.isForgotTokenValid(user));
    }
    @Then("Kullanıcıya genel bir bu eposta ile bağlantılı kullanıcı varsa şifre sıfırlama epostası gönderilmiştir mesajı")
    public void kullanıcıya_genel_bir_bu_eposta_ile_bağlantılı_kullanıcı_varsa_şifre_sıfırlama_epostası_gönderilmiştir_mesajı() throws IOException {
        String url = postRequestHelper.buildUrl(FORGOT_PATH);
        UserRequest userRequest = new UserRequest(username);
        HttpResponse response = postRequestHelper.httpPost(userRequest, url);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Given("Kullanıcının reset token süresi dolmuş")
    public void kullanıcının_reset_token_süresi_dolmuş() {
        User user = userService.getUserByUsername(username);
        user.setResetPasswordTokenExpiry(new Date());
        userService.save(user);
    }
    @Given("Kullanıcı eposta ile gelen linke tıklıyor ve uygulama şifre sıfırlama ekranı ile açılıyor")
    public void kullanıcı_eposta_ile_gelen_linke_tıklıyor_ve_uygulama_şifre_sıfırlama_ekranı_ile_açılıyor() {
        User user = userService.getUserByUsername(username);
        this.token = user.getResetPasswordToken();
    }
    @When("Kullanıcı şifresini {string} olarak giriyor")
    public void kullanıcı_şifresini_olarak_giriyor(String password) {
        this.password = password;
    }
    @When("Kullanıcı Şifreyi Değiştir tuşuna basıyor")
    public void kullanıcı_şifreyi_değiştir_tuşuna_basıyor() {
        System.out.println("Kullanıcı şifre değiştir tuşuna basıyor");
    }
    @Then("Şifre değiştirme linkinin süresinin dolduğu bilgisi veriliyor")
    public void şifre_değiştirme_linkinin_süresinin_dolduğu_bilgisi_veriliyor() throws IOException {
        String url = postRequestHelper.buildUrl(RESET_PATH);
        ResetRequest resetRequest = new ResetRequest(this.token, this.password);
        HttpResponse response = postRequestHelper.httpPost(resetRequest, url);
        assertEquals(403, response.getStatusLine().getStatusCode());
        HttpEntity entity2 = response.getEntity();
        String responseString = EntityUtils.toString(entity2, "UTF-8");
        assertTrue(responseString == null || responseString.trim().equals(""));
    }

    @Then("Şifre başarılı bir şekilde güncelleniyor")
    public void şifre_başarılı_bir_şekilde_güncelleniyor() throws IOException {
        String url = postRequestHelper.buildUrl(RESET_PATH);
        ResetRequest resetRequest = new ResetRequest(this.token, this.password);
        HttpResponse response = postRequestHelper.httpPost(resetRequest, url);
        assertEquals(200, response.getStatusLine().getStatusCode());
        HttpEntity entity2 = response.getEntity();
        String responseString = EntityUtils.toString(entity2, "UTF-8");
        assertTrue(responseString == null || responseString.trim().equals(""));
    }

    @After("@US03Last")
    public void kullaniciKaldir() {
        User user = userService.getUserByUsername(username);
        if(user != null)
            userService.deleteUser(user);
    }

}
