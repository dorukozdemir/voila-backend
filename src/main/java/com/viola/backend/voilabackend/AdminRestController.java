package com.viola.backend.voilabackend;

import java.util.ArrayList;
import java.util.List;

import com.viola.backend.voilabackend.model.domain.Admin;
import com.viola.backend.voilabackend.model.domain.Company;
import com.viola.backend.voilabackend.model.domain.Url;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.domain.UserStatus;
import com.viola.backend.voilabackend.model.dto.AdminListItem;
import com.viola.backend.voilabackend.model.dto.CardvisitListItem;
import com.viola.backend.voilabackend.model.dto.CompanyListItem;
import com.viola.backend.voilabackend.model.dto.StatisticsDto;
import com.viola.backend.voilabackend.model.dto.UrlListItem;
import com.viola.backend.voilabackend.model.dto.admin.EditProfileDto;
import com.viola.backend.voilabackend.model.web.AdminRequest;
import com.viola.backend.voilabackend.model.web.CompanyRequest;
import com.viola.backend.voilabackend.model.web.EditProfileRequest;
import com.viola.backend.voilabackend.model.web.PaginatedData;
import com.viola.backend.voilabackend.model.web.UrlCreateRequest;
import com.viola.backend.voilabackend.model.web.UrlRequest;
import com.viola.backend.voilabackend.model.web.UserSearch;
import com.viola.backend.voilabackend.service.UserService;
import com.viola.backend.voilabackend.service.AdminService;
import com.viola.backend.voilabackend.service.CompanyService;
import com.viola.backend.voilabackend.service.UrlService;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            StatisticsDto statistics = new StatisticsDto();
            return ResponseEntity.status(HttpStatus.OK).body(statistics.jsonString());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/cardvisits")
    public ResponseEntity<PaginatedData> cardvisits(@RequestParam int start, 
        @RequestParam int size, @RequestParam String name, 
        @RequestParam String surname, @RequestParam String email, 
        @RequestParam String url, @RequestParam String company, @RequestParam String all, @RequestParam String companyId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) auth.getPrincipal();
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        String searchCompanyId = "";
        if (admin.getCompany() != null) {
            searchCompanyId = admin.getCompany().getId().toString();
        } else {
            searchCompanyId = companyId;
        }
        UserSearch userSearch = new UserSearch();
        Page<User> users = null;
        if(all != null && !all.trim().equals("")) {
            users = userService.getAllUsers(start-1, size, all);
        } else {
            if(name != null && !name.trim().equals("")) {
                userSearch.setName(name);
            }
            if(surname != null && !surname.trim().equals("")) {
                userSearch.setSurname(surname);
            }
            if(url != null && !url.trim().equals("")) {
                userSearch.setUrl(url);
            }
            if(email != null && !email.trim().equals("")) {
                userSearch.setEmail(email);
            }
            if(company != null && !company.trim().equals("")) {
                List<Company> companies = companyService.findCompaniesByName(company);
                userSearch.setCompanies(companies);
            }
            users = userService.getAllUsers(start-1, size, userSearch, searchCompanyId);
        }
        
        if (admin == null || users == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            List<CardvisitListItem> list = new ArrayList<CardvisitListItem>();
            for (User u : users.toList()) {
                list.add(new CardvisitListItem(u));
            }
            PaginatedData pd = new PaginatedData(list, users.getTotalElements());
            return ResponseEntity.status(HttpStatus.OK).body(pd);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/admins")
    public ResponseEntity<List<AdminListItem>> admins(@RequestParam String search, @RequestParam String companyId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) auth.getPrincipal();
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        String searchCompanyId = "";
        if (admin.getCompany() != null) {
            searchCompanyId = admin.getCompany().getId().toString();
        } else {
            searchCompanyId = companyId;
        }
        List<Admin> admins = adminService.getAllUsers(search, searchCompanyId);
        List<AdminListItem> list = new ArrayList<AdminListItem>();
        for (Admin a : admins) {
            list.add(new AdminListItem(a));
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/admin/admin")
    public ResponseEntity<String> createAdmin(@RequestBody AdminRequest adminRequest) throws Exception {
        String username = adminRequest.getUsername();
        String password = adminRequest.getPassword();
        String name = adminRequest.getName();
        String surname = adminRequest.getSurname();
        String companyId = adminRequest.getCompany();
        Company company = null;
        if (companyId != null) {
            company = companyService.getCompanyById(companyId);
        }
        if (adminService.isUserExist(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"username\": false}");
        } else {
            Admin admin = adminService.createAdmin(username, password, name, surname, company);
            if (admin != null) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/admin/admin")
    public ResponseEntity<String> updateAdmin(@RequestBody AdminRequest adminRequest) throws Exception {
        String username = adminRequest.getUsername();
        String password = adminRequest.getPassword();
        String name = adminRequest.getName();
        String surname = adminRequest.getSurname();
        String companyId = adminRequest.getCompany();
        Company company = null;
        if (companyId != null) {
            company = companyService.getCompanyById(companyId);
        }
        Admin admin = adminService.getUserByUsername(username);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"username\": false}");
        } else {
          adminService.updateAdmin(admin, password, name, surname, company);
            return ResponseEntity.ok().build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/companies")
    public ResponseEntity<List<CompanyListItem>> companies() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) auth.getPrincipal();
        List<Company> companies = companyService.getAllCompanies();
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            List<CompanyListItem> list = new ArrayList<CompanyListItem>();
            for (Company c : companies) {
                list.add(new CompanyListItem(c));
            }
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/urls")
    public ResponseEntity<PaginatedData> urls(@RequestParam int start, @RequestParam int size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) auth.getPrincipal();
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Page<Url> urls = urlService.getAllUrls(start-1, size);
            List<UrlListItem> list = new ArrayList<UrlListItem>();
            for (Url u : urls.toList()) {
                User testUser = userService.getByProfileToken(u.getToken());
                list.add(new UrlListItem(u, testUser != null));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new PaginatedData(list, urls.getTotalElements()));
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
        } else if(companyService.isCompanyExistByEmail(companyEmail)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"email\": false}");
        } 
        
        else {
            Company company = companyService.createCompany(name, companyEmail, phone, authorityEmail, authorityName,
                    admin);
            if (company != null) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/admin/company")
    public ResponseEntity<String> updateCompany(@RequestBody CompanyRequest companyRequest) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) auth.getPrincipal();
        String companyEmail = companyRequest.getCompanyEmail();
        String name = companyRequest.getName();
        String phone = companyRequest.getPhone();
        String authorityEmail = companyRequest.getAuthorityEmail();
        String authorityName = companyRequest.getAuthorityName();
        String idString = companyRequest.getId();
        Company company = companyService.getCompanyById(idString);
        if (company == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            companyService.updateCompany(company, name, companyEmail, phone, authorityEmail, authorityName);
            return ResponseEntity.ok().build();
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/admin/url")
    public ResponseEntity<String> createUrl(@RequestBody UrlCreateRequest urlCreateRequest) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) auth.getPrincipal();
        if (urlCreateRequest.getUrls() == null || urlCreateRequest.getUrls().isEmpty()) {
            return ResponseEntity.status(HttpStatus.LENGTH_REQUIRED).build();
        } else {
            for (UrlRequest ur : urlCreateRequest.getUrls()) {
                int count = Integer.parseInt(ur.getCount());
                Company company = null;
                if(ur.getCompanyId() != null) {
                    company = companyService.getCompanyById(ur.getCompanyId());
                }
                if (count > 0) {
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

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/token/{profileToken}")
    public ResponseEntity<String> fromToken(@PathVariable String profileToken) {
        User otherUser = userService.getByProfileToken(profileToken);
        if (otherUser != null) {
            EditProfileDto profile = new EditProfileDto(otherUser);
            return ResponseEntity.status(HttpStatus.OK).body(profile.jsonString());
        } else {
            if (urlService.isUrlExist(profileToken)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/admin/profile")
    public ResponseEntity<String> updateProfile(@RequestBody EditProfileRequest profileUpdateRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) auth.getPrincipal();
        String token = profileUpdateRequest.getProfileToken();
        String name = profileUpdateRequest.getName();
        String note = profileUpdateRequest.getNote();
        String surname = profileUpdateRequest.getSurname();
        boolean isLocked = profileUpdateRequest.isLocked();
        User user = userService.getByProfileToken(token);
        boolean profileUpdated = true;
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        user.setName(name);
        user.setSurname(surname);
        user.setNote(note);
        user.setLocked(isLocked);
        userService.save(user);

        if (profileUpdated) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/admin/profile")
    public ResponseEntity<String> createProfile(@RequestBody EditProfileRequest profileUpdateRequest) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) auth.getPrincipal();
        String token = profileUpdateRequest.getProfileToken();
        String name = profileUpdateRequest.getName();
        String note = profileUpdateRequest.getNote();
        String surname = profileUpdateRequest.getSurname();
        String email = profileUpdateRequest.getEmail();
        String password = profileUpdateRequest.getPassword();
        boolean locked = profileUpdateRequest.isLocked();
        User user = userService.getByProfileToken(email);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if(!urlService.isUrlExist(token)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        userService.createUser(email, password, name, surname, token, note, locked);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/profile/reset/{profileToken}")
    public ResponseEntity<String> resetProfile(@PathVariable String profileToken) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) auth.getPrincipal();
        if(admin == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = userService.getByProfileToken(profileToken);
        
        if (user == null || user.getStatus().equals(UserStatus.SETUP)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if(admin.getCompany() != null) {
            if(user.getCompany().equals(admin.getCompany())) {
                userService.resetUser(user);
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            userService.resetUser(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/profile/active/{profileToken}")
    public ResponseEntity<String> enableProfile(@PathVariable String profileToken) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) auth.getPrincipal();
        if(admin == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = userService.getByProfileToken(profileToken);
        
        if (user == null || user.getStatus().equals(UserStatus.ACTIVE)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if(admin.getCompany() != null) {
            if(user.getCompany().equals(admin.getCompany())) {
                userService.enableUser(user);
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            userService.enableUser(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/admin/profile/passive/{profileToken}")
    public ResponseEntity<String> disableProfile(@PathVariable String profileToken) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin) auth.getPrincipal();
        if(admin == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user = userService.getByProfileToken(profileToken);
        
        if (user == null || user.getStatus().equals(UserStatus.PASSIVE)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if(admin.getCompany() != null) {
            if(user.getCompany().equals(admin.getCompany())) {
                userService.disableUser(user);
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            userService.disableUser(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}