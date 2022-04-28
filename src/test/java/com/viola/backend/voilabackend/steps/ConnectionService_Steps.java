package com.viola.backend.voilabackend.steps;

import java.util.List;

import javax.transaction.Transactional;

import com.viola.backend.voilabackend.exceptions.UserAlreadyExistException;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.service.ConnectionService;
import com.viola.backend.voilabackend.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ConnectionService_Steps {
    @Autowired
	private transient UserService userService;

    @Autowired
    private transient ConnectionService connectionService;

	private String username1, username2;


    @Given("Kullanıcı {int} {string} eposta ve {string} şifresi ile oluşturulduğunda")
    public void kullanıcı_eposta_ve_şifresi_ile_oluşturulduğunda(Integer kullaniciNo, String username, String password) throws UserAlreadyExistException {
        userService.createUser(username, password);
        if (kullaniciNo == 1) {
            this.username1 = username;
        } else {
            username2 = username;
        }
    }

    @Given("Kullanıcı {int} kullanıcı {int} yi takip ediyor")
    public void kullanıcı_kullanıcı_yi_takip_ediyor(Integer kullanici1, Integer kullanici2) {
        User user1 = userService.getUserByUsername(this.username1);
        User user2 = userService.getUserByUsername(this.username2);
        connectionService.createConnection(user1, user2);
    }
    @Transactional
    @Then("Kullanıcı {int} bağlantılarını sorguladığında kullanıcı {int} yi görüyor")
    public void kullanıcı_bağlantılarını_sorguladığında_kullanıcı_yi_görüyor(Integer kullanici1, Integer kullanici2) {
        User user1 = userService.getUserByUsername(username1);
        User user2 = userService.getUserByUsername(username2);
        List<User> connectedUsers = connectionService.getConnections(user1);
        Assertions.assertNotNull(connectedUsers);
        Assertions.assertTrue(connectedUsers.size() == 1);
        Assertions.assertTrue(user2.equals(connectedUsers.get(0)));
    }

    @After("@ConnectionServiceLast")
    public void kullaniciKaldir() {
        User user1 = userService.getUserByUsername(username1);
        if(user1 != null) {
            userService.deleteUser(user1);
        }

        User user2 = userService.getUserByUsername(username2);
        if(user2 != null) {
            userService.deleteUser(user2);
        }
    }
}
