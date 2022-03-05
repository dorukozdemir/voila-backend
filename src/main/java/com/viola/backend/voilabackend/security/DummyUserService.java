package com.viola.backend.voilabackend.security;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DummyUserService {

    private Map<String, User> users = new HashMap<>();

    @PostConstruct
    public void initialize() {
        users.put("mete", new User("mete", "$2a$10$B3A0YH1z0U56y.lz6ixjOejJLtrh8lHa4q.Kf.VALWCMAww0id88e",new ArrayList<>()));
        users.put("harun", new User("harun", "$2a$10$PIFRI1nZi8kKWiQrDgROteFu2qb9yxCBmCPF9r1OwgYANsun6jCgC",new ArrayList<>()));
        users.put("doruk", new User("doruk", "$2a$10$P/Xl4V9KRAkWz3R6hzECLO6gustYI.uDoeQrk8TF0AYTQdHVxN5OG",new ArrayList<>()));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = "mete123";
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println();
        System.out.println("Password is         : " + password);
        System.out.println("Encoded Password is : " + encodedPassword);

        password = "harun123";
        encodedPassword = passwordEncoder.encode(password);
        System.out.println("Password is         : " + password);
        System.out.println("Encoded Password is : " + encodedPassword);

        password = "doruk123";
        encodedPassword = passwordEncoder.encode(password);
        System.out.println("Password is         : " + password);
        System.out.println("Encoded Password is : " + encodedPassword);
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }
}