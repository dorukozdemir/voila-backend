package com.viola.backend.voilabackend;

import com.viola.backend.voilabackend.dto.ProfileDto;
import com.viola.backend.voilabackend.externals.EmailSenderService;
import com.viola.backend.voilabackend.model.User;
import com.viola.backend.voilabackend.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/profile/{profileToken}")
    public ResponseEntity<String> profile(@PathVariable String profileToken) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
        User otherUser = userService.getByProfileToken(profileToken);
        if (user == null || otherUser == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else { 
            System.out.println("kullanıcı dönüştürülüp gönderilecek");            
            ProfileDto profile = new ProfileDto(otherUser);
            return ResponseEntity.status(HttpStatus.OK).body(profile.jsonString());
        }
    }
}