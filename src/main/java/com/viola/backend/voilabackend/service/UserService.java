package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import com.viola.backend.voilabackend.model.User;
import com.viola.backend.voilabackend.repository.UserRepository;

@Service("userService")
public class UserService {
    @Autowired
	UserRepository userRepository;

    private final int RESETPASSWORDTOKENDURATION = 15;

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

    public boolean isForgotTokenValid(User user) {
        if (user == null) {
            return false;
        }
        if (user.getResetPasswordToken() == null) {
            return false;
        }
        if (user.getResetPasswordTokenExpiry() == null || user.getResetPasswordTokenExpiry().before(new Date())) {
            return false;
        }
        return true;
    }

    public void createResetPasswordToken(User user) {
        Calendar currentTimeNow = Calendar.getInstance();
        currentTimeNow.add(Calendar.MINUTE, RESETPASSWORDTOKENDURATION);
        Date expiry = currentTimeNow.getTime();
        user.setResetPasswordToken(UUID.randomUUID().toString());
        user.setResetPasswordTokenExpiry(expiry);
        userRepository.save(user);
    }
}
