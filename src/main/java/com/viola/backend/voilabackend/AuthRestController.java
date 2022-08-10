package com.viola.backend.voilabackend;

import java.net.URI;

import com.viola.backend.voilabackend.externals.EmailSenderService;
import com.viola.backend.voilabackend.jwt.JwtUtil;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.web.ResetRequest;
import com.viola.backend.voilabackend.model.web.UserRequest;
import com.viola.backend.voilabackend.security.CustomUserDetailsService;
import com.viola.backend.voilabackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailSenderService emailSenderService;

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest authRequest) {
        User user = userService.getUserByUsername(authRequest.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"username\": false}");
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"password\": false}");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok()
            .body(jwt);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest authRequest) throws Exception {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        if (userService.isUserExist(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"username\": false}");
        } else {
            User user = userService.createUser(username, password);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.created(new URI(""))
                .body(jwt);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/forgot")
    public ResponseEntity<String> forgot(@RequestBody UserRequest authRequest) throws Exception {
        String username = authRequest.getUsername();
        if (userService.isUserExist(username)) {
            User user = userService.getUserByUsername(username);
            userService.createResetPasswordToken(user);
            emailSenderService.sendForgotPasswordEmail(user.getName() == null ? "" : user.getName(), user.getUsername(), user.getResetPasswordToken());
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<String> reset(@RequestBody ResetRequest authRequest) throws Exception {
        String token = authRequest.getToken();
        String password = authRequest.getPassword();
        if (userService.isForgotTokenValid(token)) {
            userService.changePasswordByToken(token, password);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}