package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import com.viola.backend.voilabackend.model.domain.BankAccountInfo;
import com.viola.backend.voilabackend.model.domain.CompanyInfo;
import com.viola.backend.voilabackend.model.domain.ContactInfo;
import com.viola.backend.voilabackend.model.domain.Link;
import com.viola.backend.voilabackend.model.domain.SocialMediaAccounts;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.dto.UserDto;
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

    public User getByPasswordResetToken(String token) {
        List<User> users = userRepository.findByResetPasswordToken(token);
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

    public void save(User user) {
        userRepository.save(user);
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

    public boolean isForgotTokenValid(String token) {
        if (token == null || token.trim().equals("")) {
            return false;
        }
        User user = getByPasswordResetToken(token);
        return isForgotTokenValid(user);
    }

    public void createResetPasswordToken(User user) {
        Calendar currentTimeNow = Calendar.getInstance();
        currentTimeNow.add(Calendar.MINUTE, RESETPASSWORDTOKENDURATION);
        Date expiry = currentTimeNow.getTime();
        user.setResetPasswordToken(UUID.randomUUID().toString());
        user.setResetPasswordTokenExpiry(expiry);
        userRepository.save(user);
    }

    public void changePasswordByToken(String token, String password) {
        User user = getByPasswordResetToken(token);
        String encodedPassword = passwordEncoder.encode(password);
        if (user != null) {
            user.setPassword(encodedPassword);
            save(user);
        } else {
        }
    }

    public User getByProfileToken(String profileToken) {
        List<User> users = userRepository.findByProfileToken(profileToken);
        if (CollectionUtils.isNotEmpty(users)) {
            return users.get(0);
        }
		return null;
    }

    public void updateSocialMediaAccounts(User user, SocialMediaAccounts socialMediaAccounts) {
        if (user.getSocialMediaAccounts() == null) {
            socialMediaAccounts.setUser(user);
            user.setSocialMediaAccounts(socialMediaAccounts);
            save(user);
        } else {
            user.updateSocialMediaAccounts(socialMediaAccounts);
            save(user);
        }
    }

    public void addContactInfo(User user, ContactInfo contactInfo) {
        contactInfo.setUser(user);
        user.addContactInfo(contactInfo);
        save(user);
    }

    public void addLink(User user, Link link) {
        link.setUser(user);
        user.addLink(link);
        save(user);
    }

    @Transactional
    public void addCompanyInfo(User user, CompanyInfo companyInfo) {
        companyInfo.setUser(user);
        user.addCompanyInfo(companyInfo);
        save(user);
    }

    public void addBankAccountInfo(User user, BankAccountInfo bankAccountInfo) {
        bankAccountInfo.setUser(user);
        user.addBankAccountInfo(bankAccountInfo);
        save(user);
    }

    public void updatePersonalInformation(User user, UserDto profile) {
        updatePersonalInformation(user, profile.getName(), profile.getSurname(), profile.getBio());
    }

    public void updatePersonalInformation(User user, String name, String surname, String bio ) {
        if(name != null) {
            user.setName(name);
        }
        if(surname != null) {
            user.setSurname(surname);
        }
        if(bio != null) {
            user.setBio(bio);
        }
        save(user);
    }
}
