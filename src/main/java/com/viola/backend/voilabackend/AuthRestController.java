package com.viola.backend.voilabackend;

import com.viola.backend.voilabackend.jwt.JwtUtil;
import com.viola.backend.voilabackend.model.User;
import com.viola.backend.voilabackend.model.UserRequest;
import com.viola.backend.voilabackend.security.CustomUserDetailsService;
import com.viola.backend.voilabackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorret username or password", ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest authRequest) throws Exception {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        if (userService.isUserExist(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            User user = userService.createUser(username, password);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok()
                .body(jwt);
        }
    }
}