package com.viola.backend.voilabackend.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.viola.backend.voilabackend.exceptions.UserAlreadyExistException;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserService_Steps {

	@Autowired
	private transient UserService userService;

	private String username;
	private String profileToken;
	
	@When("Kullanıcı {string} ve {string} ile oluşturulduğunda")
	public void kullanıcı_ve_ile_oluşturulduğunda(String username, String password) throws UserAlreadyExistException {
		this.username = username;
		userService.createUser(username, password);

	}
	@Then("Kullanıcının profile tokenı oluşturulacak")
	public void kullanıcının_profile_tokenı_oluşturulacak() {
		User user = userService.getUserByUsername(this.username);
		assertNotNull(user.getProfileToken());
	}

	@Given("Kullanıcı {string} adresi ile kaydoldu")
	public void kullanıcı_adresi_ile_kaydoldu(String username) {
		this.username = username;
	}
	@When("Kullanıcı profile token ı ile arandığında")
	public void kullanıcı_profile_token_ı_ile_arandığında() {
		User user = userService.getUserByUsername(this.username);
		assertNotNull(user);
		assertNotNull(user.getProfileToken());
		this.profileToken = user.getProfileToken();
	}
	@Then("{string} eposta adresli kullanıcı bulunabilecek")
	public void eposta_adresli_kullanıcı_bulunabilecek(String string) {
		User user = userService.getByProfileToken(this.profileToken);
		assertEquals(this.username, user.getUsername());;
	}

	@After("@UserServiceLast")
    public void kullaniciKaldir() {
        User user = userService.getUserByUsername(username);
        if(user != null)
            userService.deleteUser(user);
    }
}
