package com.viola.backend.voilabackend.security;

import com.viola.backend.voilabackend.model.domain.Admin;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.service.AdminService;
import com.viola.backend.voilabackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getUserByUsername(username);
    }

    public Admin loadAdminByUsername(String username) throws UsernameNotFoundException {
        return adminService.getUserByUsername(username);
    }
}