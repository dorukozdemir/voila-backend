package com.viola.backend.voilabackend.security;

import com.viola.backend.voilabackend.service.AdminService;
import com.viola.backend.voilabackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String usernamePart = null;
        String userTypePart = null;
        if (username.contains(":")) {
            int colonIndex = username.lastIndexOf(":");
            usernamePart = username.substring(0, colonIndex);
            userTypePart = username.substring(colonIndex + 1, username.length());
        }
        usernamePart = usernamePart == null ? username : usernamePart;
        if (userTypePart == null || userTypePart.equals("USER")) {
            return userService.getUserByUsername(usernamePart);
        } else {
            return adminService.getUserByUsername(usernamePart);
        }
    }
}