package com.viola.backend.voilabackend;

import com.viola.backend.voilabackend.externals.EmailSenderService;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.dto.ProfileDto;
import com.viola.backend.voilabackend.model.web.ProfileRequest;
import com.viola.backend.voilabackend.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/profile/token")
    public ResponseEntity<String> profileToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
        if (user == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {         
            return ResponseEntity.status(HttpStatus.OK).body(user.getProfileToken());
        }
    }

    @GetMapping("/profile/{profileToken}")
    public ResponseEntity<String> profile(@PathVariable String profileToken) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
        User otherUser = userService.getByProfileToken(profileToken);
        if (user == null || otherUser == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {         
            ProfileDto profile = new ProfileDto(otherUser);
            return ResponseEntity.status(HttpStatus.OK).body(profile.jsonString());
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody ProfileRequest profileUpdateRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
        boolean profileUpdated = false;
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (profileUpdateRequest.getPersonal() != null) {
            userService.updatePersonalInformation(user, profileUpdateRequest.getPersonal());
            profileUpdated = true;
        }
        if (profileUpdateRequest.getSocialMediaAccounts() != null) {
            userService.updateSocialMediaAccounts(user, profileUpdateRequest.getSocialMediaAccounts());
            profileUpdated = true;
        }if (profileUpdateRequest.getContactInfo() != null && profileUpdateRequest.getContactInfo().length > 0) {
            userService.updateContactInformation(user, profileUpdateRequest.getContactInfo());
            profileUpdated = true;
        }
        if(profileUpdated) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/profile")
    public ResponseEntity<String> myProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
        User profileUser = userService.getUserByUsername(user.getUsername());
        if (profileUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {         
            ProfileDto profile = new ProfileDto(profileUser);
            return ResponseEntity.status(HttpStatus.OK).body(profile.jsonString());
        }
    }
}