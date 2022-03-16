package com.viola.backend.voilabackend.steps;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.viola.backend.voilabackend.helper.JsonDataReader;
import com.viola.backend.voilabackend.model.SocialMediaAccounts;
import com.viola.backend.voilabackend.model.User;
import com.viola.backend.voilabackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserProfile_Steps {
    private String username;
    private User user;
    @Autowired
    private transient UserService userService;
    @Autowired
    private transient JsonDataReader jsonDataReader;
    
    @Given("Kullanıcı {string} eposta ve {string} şifresi ile oluşturulduğunda")
    public void kullanıcı_eposta_ve_şifresi_ile_oluşturulduğunda(String username, String password) {
        this.username = username;
		userService.createUser(username, password);
    }
    @Given("Örnek profil verisi kaydedildi")
    public void örnek_profil_verisi_kaydedildi() {
        User user = userService.getUserByUsername(this.username);
        this.user = user;
        SocialMediaAccounts socialMediaAccounts = jsonDataReader.getSocialMediaAccounts();
        userService.updateSocialMediaAccounts(user, socialMediaAccounts);
        
    }
    @Then("Kullanıcının profil verisi örnek ile aynı")
    public void kullanıcının_profil_verisi_örnek_ile_aynı() {
        SocialMediaAccounts socialMediaAccounts = jsonDataReader.getSocialMediaAccounts();
        if (this.user != null && this.user.getSocialMediaAccounts() != null) {
            assertTrue(testSocialMediaAccounts(this.user.getSocialMediaAccounts(), socialMediaAccounts));
        } else {
            fail();
        }
    }
    @Given("Kullanıcı {string} epostası ile var")
    public void kullanıcı_epostası_ile_var(String username) {
        this.username = username;
        User user = userService.getUserByUsername(username);
        if (user == null) {
            fail();
        }
        this.user = user;
        System.out.println("bu aşamayı geçti");
    }
    @When("Kullanıcının sosyal medya hesapları örnek veri ile güncellendiğinde")
    public void kullanıcının_sosyal_medya_hesapları_örnek_veri_ile_güncellendiğinde() {
        SocialMediaAccounts socialMediaAccountsUpdated = jsonDataReader.getSocialMediaAccountsUpdated();
        userService.updateSocialMediaAccounts(this.user, socialMediaAccountsUpdated);
    }
    @Then("Sosyal medya hesapları güncellenmiş olacak")
    public void sosyal_medya_hesapları_güncellenmiş_olacak() {
        SocialMediaAccounts socialMediaAccountsUpdated = jsonDataReader.getSocialMediaAccountsUpdated();
        SocialMediaAccounts socialMediaAccounts = jsonDataReader.getSocialMediaAccounts();
        User updatedUser = userService.getUserByUsername(this.username);
        assertFalse(testSocialMediaAccounts(updatedUser.getSocialMediaAccounts(), socialMediaAccounts));
        assertTrue(testSocialMediaAccounts(updatedUser.getSocialMediaAccounts(), socialMediaAccountsUpdated));
    }

    private boolean testSocialMediaAccounts(SocialMediaAccounts socialMediaAccounts1, SocialMediaAccounts socialMediaAccounts2) {
        if (!socialMediaAccounts1.getFacebook().equals(socialMediaAccounts2.getFacebook())) {
            return false;
        }
        if (!socialMediaAccounts1.getTwitter().equals(socialMediaAccounts2.getTwitter())) {
            return false;
        }
        if (!socialMediaAccounts1.getInstagram().equals(socialMediaAccounts2.getInstagram())) {
            return false;
        }
        if (!socialMediaAccounts1.getTiktok().equals(socialMediaAccounts2.getTiktok())) {
            return false;
        }
        if (!socialMediaAccounts1.getYoutube().equals(socialMediaAccounts2.getYoutube())) {
            return false;
        }
        if (!socialMediaAccounts1.getLinkedin().equals(socialMediaAccounts2.getLinkedin())) {
            return false;
        }
        if (!socialMediaAccounts1.getBehance().equals(socialMediaAccounts2.getBehance())) {
            return false;
        }
        if (!socialMediaAccounts1.getDribble().equals(socialMediaAccounts2.getDribble())) {
            return false;
        }
        if (!socialMediaAccounts1.getPinterest().equals(socialMediaAccounts2.getPinterest())) {
            return false;
        }
        if (!socialMediaAccounts1.getGithub().equals(socialMediaAccounts2.getGithub())) {
            return false;
        }
        if (!socialMediaAccounts1.getTelegram().equals(socialMediaAccounts2.getTelegram())) {
            return false;
        }
        if (!socialMediaAccounts1.getSnap().equals(socialMediaAccounts2.getSnap())) {
            return false;
        } 
        return true; 
    }
}
