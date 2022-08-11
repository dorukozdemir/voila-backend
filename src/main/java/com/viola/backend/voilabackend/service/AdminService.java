package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import com.viola.backend.voilabackend.exceptions.AdminAlreadyExistException;
import com.viola.backend.voilabackend.model.domain.Admin;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.repository.AdminRepository;
import com.viola.backend.voilabackend.security.VoilaPasswordEncoder;

@Service("adminService")
public class AdminService {
    @Autowired
	private AdminRepository adminRepository;

    private final int RESETPASSWORDTOKENDURATION = 15;

    private final VoilaPasswordEncoder passwordEncoder = new VoilaPasswordEncoder();
    
    public Admin getUserByUsername(String username) {
        List<Admin> admins = adminRepository.findByUsername(username);
        if (CollectionUtils.isNotEmpty(admins)) {
            return admins.get(0);
        }
		return null;
	}

    public Admin getByPasswordResetToken(String token) {
        List<Admin> admins = adminRepository.findByResetPasswordToken(token);
        if (CollectionUtils.isNotEmpty(admins)) {
            return admins.get(0);
        }
		return null;
    }

    public boolean isUserExist(String username) {
        Admin admin = this.getUserByUsername(username);
        return (admin != null);
    }

    public Admin createAdmin(String username, String password) throws AdminAlreadyExistException {
        Admin admin = getUserByUsername(username);
        if (admin != null)
            throw new AdminAlreadyExistException();
        User newUser = new User(username, passwordEncoder.encode(password));
        return adminRepository.save(admin);
    }

    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    public boolean isForgotTokenValid(Admin admin) {
        if (admin == null) {
            return false;
        }
        if (admin.getResetPasswordToken() == null) {
            return false;
        }
        if (admin.getResetPasswordTokenExpiry() == null || admin.getResetPasswordTokenExpiry().before(new Date())) {
            return false;
        }
        return true;
    }

    public boolean isForgotTokenValid(String token) {
        if (token == null || token.trim().equals("")) {
            return false;
        }
        Admin admin = getByPasswordResetToken(token);
        return isForgotTokenValid(admin);
    }

    public void createResetPasswordToken(Admin admin) {
        Calendar currentTimeNow = Calendar.getInstance();
        currentTimeNow.add(Calendar.MINUTE, RESETPASSWORDTOKENDURATION);
        Date expiry = currentTimeNow.getTime();
        admin.setResetPasswordToken(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        admin.setResetPasswordTokenExpiry(expiry);
        adminRepository.save(admin);
    }

    public void changePasswordByToken(String token, String password) {
        Admin admin = getByPasswordResetToken(token);
        String encodedPassword = passwordEncoder.encode(password);
        if (admin != null) {
            admin.setPassword(encodedPassword);
            save(admin);
        } else {
        }
    }

    public List<Admin> getAllUsers() {
        return adminRepository.findAll();
    }
}
