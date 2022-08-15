package com.viola.backend.voilabackend;

import java.util.ArrayList;
import java.util.List;

import com.viola.backend.voilabackend.model.domain.Admin;
import com.viola.backend.voilabackend.model.domain.Company;
import com.viola.backend.voilabackend.model.domain.Url;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.dto.AdminListItem;
import com.viola.backend.voilabackend.model.dto.CardvisitListItem;
import com.viola.backend.voilabackend.model.dto.CompanyListItem;
import com.viola.backend.voilabackend.model.dto.StatisticsDto;
import com.viola.backend.voilabackend.model.dto.UrlListItem;
import com.viola.backend.voilabackend.model.web.AdminRequest;
import com.viola.backend.voilabackend.model.web.CompanyRequest;
import com.viola.backend.voilabackend.model.web.UrlCreateRequest;
import com.viola.backend.voilabackend.model.web.UrlRequest;
import com.viola.backend.voilabackend.service.UserService;
import com.viola.backend.voilabackend.service.AdminService;
import com.viola.backend.voilabackend.service.CompanyService;
import com.viola.backend.voilabackend.service.UrlService;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AdminRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UrlService urlService;

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

    @CrossOrigin(origins = "*")
    @PostMapping("/admin/admin")
    public ResponseEntity<String> createAdmin(@RequestBody AdminRequest authRequest) throws Exception {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        String name = authRequest.getName();
        String surname = authRequest.getSurname();
        if (adminService.isUserExist(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"username\": false}");
        } else {
            Admin admin = adminService.createAdmin(username, password, name, surname);
            if(admin != null) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/companies")
    public ResponseEntity<List<CompanyListItem>> companies() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Admin admin = (Admin) auth.getPrincipal();
        List<Company> companies = companyService.getAllCompanies();
        if (admin == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {     
            List<CompanyListItem> list = new ArrayList<CompanyListItem>();      
            for(Company c: companies) {
                list.add(new CompanyListItem(c));
            }   
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/urls")
    public ResponseEntity<List<UrlListItem>> urls() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Admin admin = (Admin) auth.getPrincipal();
        List<Url> urls = urlService.getAllUrls();
        if (admin == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {     
            List<UrlListItem> list = new ArrayList<UrlListItem>();      
            for(Url u: urls) {
                list.add(new UrlListItem(u));
            }   
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/admin/company")
    public ResponseEntity<String> createCompany(@RequestBody CompanyRequest companyRequest) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Admin admin = (Admin) auth.getPrincipal();
        String companyEmail = companyRequest.getCompanyEmail();
        String name = companyRequest.getName();
        String phone = companyRequest.getPhone();
        String authorityEmail = companyRequest.getAuthorityEmail();
        String authorityName = companyRequest.getAuthorityName();
        if (companyService.isCompanyExist(name)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"name\": false}");
        } else {
            Company company = companyService.createCompany(name, companyEmail, phone, authorityEmail, authorityName, admin);
            if(company != null) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/admin/url")
    public ResponseEntity<String> createUrl(@RequestBody UrlCreateRequest urlCreateRequest) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Admin admin = (Admin) auth.getPrincipal();
        if (urlCreateRequest.getUrls()== null || urlCreateRequest.getUrls().isEmpty()) {
            return ResponseEntity.status(HttpStatus.LENGTH_REQUIRED).build();
        } else {
            for(UrlRequest ur : urlCreateRequest.getUrls()) {
                int count = Integer.parseInt(ur.getCount());
                Company company = companyService.getCompanyById(ur.getCompanyId());
                if(count > 0) {
                    for (int i = 0; i < count; i++) {
                        Url url = new Url();
                        url.setAdmin(admin);
                        url.setCompany(company);
                        urlService.save(url);
                    }
                }
            }
            return ResponseEntity.ok().build();
        }
    }
}