package com.viola.backend.voilabackend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.viola.backend.voilabackend.externals.EmailSenderService;
import com.viola.backend.voilabackend.model.domain.Connection;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.dto.ProfileDto;
import com.viola.backend.voilabackend.model.dto.UserDto;
import com.viola.backend.voilabackend.model.web.ProfileRequest;
import com.viola.backend.voilabackend.model.web.UploadImageRequest;
import com.viola.backend.voilabackend.model.web.UploadImageResponse;
import com.viola.backend.voilabackend.service.ConnectionService;
import com.viola.backend.voilabackend.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class ProfileRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConnectionService connectionService;

    private static HttpURLConnection con;

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
            if (!user.equals(otherUser)) {
                Connection connection = connectionService.getConnection(user, otherUser);
                if (connection == null) {
                    connectionService.createConnection(user, otherUser);
                }
                userService.increaseProfileVisitCount(otherUser);
            }
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
        }
        if (profileUpdateRequest.getContactInfo() != null && profileUpdateRequest.getContactInfo().length > 0) {
            userService.updateContactInformation(user, profileUpdateRequest.getContactInfo());
            profileUpdated = true;
        }
        if (profileUpdateRequest.getLinks() != null && profileUpdateRequest.getLinks().length > 0) {
            userService.updateLinks(user, profileUpdateRequest.getLinks());
            profileUpdated = true;
        }
        if (profileUpdateRequest.getCompanyInfo() != null && profileUpdateRequest.getCompanyInfo().length > 0) {
            userService.updateCompanyInformation(user, profileUpdateRequest.getCompanyInfo());
            profileUpdated = true;
        }
        if (profileUpdateRequest.getBankAccountInfo() != null && profileUpdateRequest.getBankAccountInfo().length > 0) {
            userService.updateBankInformation(user, profileUpdateRequest.getBankAccountInfo());
            profileUpdated = true;
        }
        if(profileUpdated) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @CrossOrigin(origins = "*")
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

    @CrossOrigin(origins = "*")
    @GetMapping("/connections")
    public ResponseEntity<List<UserDto>> myConnections() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
        List<User> connections = connectionService.getConnections(user);
        if (connections == null || connections.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            List<UserDto> connectionsDto = new ArrayList<UserDto>();      
            for(User u: connections) {
                connectionsDto.add(new UserDto(u));
            }   
            return ResponseEntity.status(HttpStatus.OK).body(connectionsDto);
        }
    }

    @GetMapping("/insights")
    public ResponseEntity<List<UserDto>> insights() {
        List<User> users = userService.getInsights();
        if (users == null || users.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            List<UserDto> usersDto = new ArrayList<UserDto>();      
            for(User u: users) {
                usersDto.add(new UserDto(u));
            }   
            return ResponseEntity.status(HttpStatus.OK).body(usersDto);
        }
    }

    @PostMapping("/uploadPhoto")
    public ResponseEntity<String> uploadPhoto(@RequestBody UploadImageRequest uploadImageRequest) {
        Gson gson = new Gson();
        var url = "https://voilacard.com/api/cardvisit/upload-image ";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
        try {

            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("x-api-key", "633e382f0f4e0ef3c9a8c9e98d5534078c94386f81343ab48c25dab9becc220f");
            con.setRequestProperty("Content-Type", "application/json");

            String jsonInputString = "{\"user\": \"" + user.getProfileToken()+ "\", \"image\": \"" + uploadImageRequest.getImageValue() + "\"}";

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);			
            }

            try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                String responseString = response.toString();
                
                System.out.println(response.toString());
                UploadImageResponse responseObject = gson.fromJson(responseString, UploadImageResponse.class);
                if(responseObject.isStatus()) {
                    userService.updatePhoto(user, responseObject.getPath());
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            con.disconnect();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/removePhoto")
    public ResponseEntity<String> removePhoto() {        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
        userService.removePhoto(user);          
        return ResponseEntity.ok().build();
    }
}