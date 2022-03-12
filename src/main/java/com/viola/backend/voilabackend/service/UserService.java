package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import com.viola.backend.voilabackend.model.User;
import com.viola.backend.voilabackend.repository.UserRepository;

@Service("userService")
public class UserService {
    @Autowired
	UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public User getUserByUsername(String username) {
        List<User> users = userRepository.findByUsername(username);
        if (CollectionUtils.isNotEmpty(users)) {
            return users.get(0);
        }
		return null;
	}

    public boolean isUserExist(String username) {
        User user = this.getUserByUsername(username);
        return (user != null);
    }

    public User createUser(String username, String password) {
        User user = new User(username, passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
