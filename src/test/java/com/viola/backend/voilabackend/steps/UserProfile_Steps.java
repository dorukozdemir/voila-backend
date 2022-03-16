package com.viola.backend.voilabackend.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.viola.backend.voilabackend.helper.JsonDataReader;
import com.viola.backend.voilabackend.model.ContactInfo;
import com.viola.backend.voilabackend.model.ContactType;
import com.viola.backend.voilabackend.model.Link;
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
    @When("Kullanıcı biosunu {string} olarak değiştirdiğinde")
    public void kullanıcı_biosunu_olarak_değiştirdiğinde(String bio) {
        this.user.setBio(bio);
        userService.save(this.user);
    }
    @Then("Kullanıcının biosu {string} olarak değişmiş olması gerekiyor")
    public void kullanıcının_biosu_olarak_değişmiş_olması_gerekiyor(String bio) {
        User updatedUser = userService.getUserByUsername(this.username);
        assertEquals(updatedUser.getBio(), bio);
    }
    @When("Kullanıcı {string} {string} whatsapp numarası giriyor")
    public void kullanıcı_whatsapp_numarası_giriyor(String extension, String value) {
        ContactInfo contactInfo = new ContactInfo(ContactType.WHATSAPP, extension, value);
        User user = userService.getUserByUsername(this.username);
        userService.addContactInfo(user, contactInfo);

    }
    @When("Kullanıcı {string} {string} telefon numarası giriyor")
    public void kullanıcı_telefon_numarası_giriyor(String extension, String value) {
        ContactInfo contactInfo = new ContactInfo(ContactType.PHONE, extension, value);
        User user = userService.getUserByUsername(this.username);
        userService.addContactInfo(user, contactInfo);
    }
    @When("Kullanıcı {string} email adresi giriyor")
    public void kullanıcı_email_adresi_giriyor(String email) {
        ContactInfo contactInfo = new ContactInfo(ContactType.EMAIL, "", email);
        User user = userService.getUserByUsername(this.username);
        userService.addContactInfo(user, contactInfo);
    }
    @Then("Kullanıcı {int} adet iletişim bilgisi girmiş oluyor")
    public void kullanıcı_adet_iletişim_bilgisi_girmiş_oluyor(Integer count) {
        User updatedUser = userService.getUserByUsername(this.username);
        assertEquals(count, updatedUser.getContactInfo().size());
    }

    @When("Kullanıcı {string} bağlantısını ekliyor")
    public void kullanıcı_bağlantısını_ekliyor(String value) {
        Link link = new Link(value);
        User user = userService.getUserByUsername(this.username);
        userService.addLink(user, link);
    }
    @Then("Kullanıcı {int} adet bağlantı eklemiş oluyor")
    public void kullanıcı_adet_bağlantı_eklemiş_oluyor(Integer count) {
        User updatedUser = userService.getUserByUsername(this.username);
        assertEquals(count, updatedUser.getLinks().size());
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
