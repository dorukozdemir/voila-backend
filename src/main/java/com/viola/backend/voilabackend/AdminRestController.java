package com.viola.backend.voilabackend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.viola.backend.voilabackend.externals.EmailSenderService;
import com.viola.backend.voilabackend.model.domain.Admin;
import com.viola.backend.voilabackend.model.domain.Connection;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.dto.AdminListItem;
import com.viola.backend.voilabackend.model.dto.CardvisitListItem;
import com.viola.backend.voilabackend.model.dto.ProfileDto;
import com.viola.backend.voilabackend.model.dto.StatisticsDto;
import com.viola.backend.voilabackend.model.dto.UserDto;
import com.viola.backend.voilabackend.model.web.ProfileRequest;
import com.viola.backend.voilabackend.model.web.UploadImageRequest;
import com.viola.backend.voilabackend.model.web.UploadImageResponse;
import com.viola.backend.voilabackend.service.ConnectionService;
import com.viola.backend.voilabackend.service.UserService;
import com.viola.backend.voilabackend.service.AdminService;

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
public class AdminRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;


    private static HttpURLConnection con;

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/dashboard")
    public ResponseEntity<String> profileToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Admin admin = (Admin) auth.getPrincipal();
        if (admin == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {     
             StatisticsDto statistics  = new StatisticsDto();  
            return ResponseEntity.status(HttpStatus.OK).body(statistics.jsonString());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/cardvisits")
    public ResponseEntity<List<CardvisitListItem>> cardvisits() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Admin admin = (Admin) auth.getPrincipal();
        List<User> users = userService.getAllUsers();
        if (admin == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {     
            List<CardvisitListItem> list = new ArrayList<CardvisitListItem>();      
            for(User u: users) {
                list.add(new CardvisitListItem(u));
            }   
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/admins")
    public ResponseEntity<List<AdminListItem>> admins() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Admin admin = (Admin) auth.getPrincipal();
        List<Admin> admins = adminService.getAllUsers();
        if (admin == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {     
            List<AdminListItem> list = new ArrayList<AdminListItem>();      
            for(Admin a: admins) {
                list.add(new AdminListItem(a));
            }   
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
    }
}