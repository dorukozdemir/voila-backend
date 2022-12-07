package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import com.viola.backend.voilabackend.exceptions.UserAlreadyExistException;
import com.viola.backend.voilabackend.model.domain.BankAccountInfo;
import com.viola.backend.voilabackend.model.domain.Company;
import com.viola.backend.voilabackend.model.domain.CompanyInfo;
import com.viola.backend.voilabackend.model.domain.Connection;
import com.viola.backend.voilabackend.model.domain.ContactInfo;
import com.viola.backend.voilabackend.model.domain.ContactType;
import com.viola.backend.voilabackend.model.domain.Link;
import com.viola.backend.voilabackend.model.domain.SocialMediaAccounts;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.domain.UserStatus;
import com.viola.backend.voilabackend.model.dto.BankAccountInfoDto;
import com.viola.backend.voilabackend.model.dto.CompanyInfoDto;
import com.viola.backend.voilabackend.model.dto.ContactInfoDto;
import com.viola.backend.voilabackend.model.dto.LinkDto;
import com.viola.backend.voilabackend.model.dto.SettingsDto;
import com.viola.backend.voilabackend.model.dto.SocialMediaAccountsDto;
import com.viola.backend.voilabackend.model.dto.UserDto;
import com.viola.backend.voilabackend.model.web.UserSearch;
import com.viola.backend.voilabackend.repository.UserRepository;
import com.viola.backend.voilabackend.security.VoilaPasswordEncoder;

@Service("userService")
public class UserService {
    @Autowired
	private UserRepository userRepository;

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private CompanyService companyService;

    private final int RESETPASSWORDTOKENDURATION = 15;

    private final VoilaPasswordEncoder passwordEncoder = new VoilaPasswordEncoder();

    private final String thumbnail = "/CardvisitUI/Dash/media/avatars/blank.png";
    
    public User getUserByUsername(String username) {
        List<User> users = userRepository.findByUsername(username);
        if (CollectionUtils.isNotEmpty(users)) {
            return users.get(0);
        }
		return null;
	}

    public User getByPasswordResetToken(String token) {
        List<User> users = userRepository.findByResetPasswordToken(token);
        if (CollectionUtils.isNotEmpty(users)) {
            return users.get(0);
        }
		return null;
    }

    public boolean isUserExist(String username) {
        User user = this.getUserByUsername(username);
        return (user != null);
    }

    public User createUser(String username, String password) throws UserAlreadyExistException {
        User user = getUserByUsername(username);
        if (user != null)
            throw new UserAlreadyExistException();
        User newUser = new User(username, passwordEncoder.encode(password));
        return userRepository.save(newUser);
    }

    public void deleteUser(User user) {
        //delete all connections
        List<Connection> connections = connectionService.getUserConnections(user);
        for (Connection connection: connections) {
            connectionService.deleteConnection(connection);
        }
        //than delete user
        userRepository.delete(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    

    public boolean isForgotTokenValid(User user) {
        if (user == null) {
            return false;
        }
        if (user.getResetPasswordToken() == null) {
            return false;
        }
        if (user.getResetPasswordTokenExpiry() == null || user.getResetPasswordTokenExpiry().before(new Date())) {
            return false;
        }
        return true;
    }

    public boolean isForgotTokenValid(String token) {
        if (token == null || token.trim().equals("")) {
            return false;
        }
        User user = getByPasswordResetToken(token);
        return isForgotTokenValid(user);
    }

    public void createResetPasswordToken(User user) {
        Calendar currentTimeNow = Calendar.getInstance();
        currentTimeNow.add(Calendar.MINUTE, RESETPASSWORDTOKENDURATION);
        Date expiry = currentTimeNow.getTime();
        user.setResetPasswordToken(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        user.setResetPasswordTokenExpiry(expiry);
        userRepository.save(user);
    }

    public void changePasswordByToken(String token, String password) {
        User user = getByPasswordResetToken(token);
        String encodedPassword = passwordEncoder.encode(password);
        if (user != null) {
            user.setPassword(encodedPassword);
            save(user);
        } else {
        }
    }

    public User getByProfileToken(String profileToken) {
        List<User> users = userRepository.findByProfileToken(profileToken);
        if (CollectionUtils.isNotEmpty(users)) {
            return users.get(0);
        }
		return null;
    }

    public void updateSocialMediaAccounts(User user, SocialMediaAccounts socialMediaAccounts) {
        if (user.getSocialMediaAccounts() == null) {
            socialMediaAccounts.setUser(user);
            user.setSocialMediaAccounts(socialMediaAccounts);
            save(user);
        } else {
            socialMediaAccounts.setUser(user);
            user.updateSocialMediaAccounts(socialMediaAccounts);
            save(user);
        }
    }

    public void updateSocialMediaAccounts(User user, SocialMediaAccountsDto socialMediaAccountsDto) {
        if (user.getSocialMediaAccounts() == null) {
            SocialMediaAccounts socialMediaAccounts = new SocialMediaAccounts();
            socialMediaAccounts.setUser(user);
            socialMediaAccounts.copyFromDto(socialMediaAccountsDto);
            user.setSocialMediaAccounts(socialMediaAccounts);
            save(user);
        } else {
            SocialMediaAccounts socialMediaAccounts = user.getSocialMediaAccounts();
            socialMediaAccounts.copyFromDto(socialMediaAccountsDto);
            user.updateSocialMediaAccounts(socialMediaAccounts);
            save(user);
        }
    }

    public void addContactInfo(User user, ContactInfo contactInfo) {
        contactInfo.setUser(user);
        user.addContactInfo(contactInfo);
        save(user);
    }

    public void addLink(User user, Link link) {
        link.setUser(user);
        user.addLink(link);
        save(user);
    }

    @Transactional
    public void addCompanyInfo(User user, CompanyInfo companyInfo) {
        companyInfo.setUser(user);
        user.addCompanyInfo(companyInfo);
        save(user);
    }

    public void addBankAccountInfo(User user, BankAccountInfo bankAccountInfo) {
        bankAccountInfo.setUser(user);
        user.addBankAccountInfo(bankAccountInfo);
        save(user);
    }

    public void updatePersonalInformation(User user, UserDto profile) {
        updatePersonalInformation(user, profile.getName(), profile.getSurname(), profile.getBio());
    }

    public void updatePersonalInformation(User user, String name, String surname, String bio ) {
        if(name != null) {
            user.setName(name);
        }
        if(surname != null) {
            user.setSurname(surname);
        }
        if(bio != null) {
            user.setBio(bio);
        }
        save(user);
    }

    public void updateContactInformation(User user, ContactInfoDto[] contactInfo) {
        user.setContactInfo(new HashSet<ContactInfo>());
        save(user);
        for(ContactInfoDto contactInfoDto : contactInfo) {
            ContactInfo ci = new ContactInfo();
            ci.setContactType(ContactType.toContactType(contactInfoDto.getContactType()));
            ci.setExtension(contactInfoDto.getExtension());
            ci.setValue(contactInfoDto.getValue());
            ci.setUser(user);
            this.addContactInfo(user, ci);
        }

        
    }

    public void updateCompanyInformation(User user, CompanyInfoDto[] companyInfo) {
        user.setCompanies(new HashSet<CompanyInfo>());
        save(user);
        for(CompanyInfoDto cid : companyInfo) {
            CompanyInfo ci = new CompanyInfo();
            ci.setName(cid.getName());
            ci.setAddress(cid.getAddress());
            ci.setTaxNo(cid.getTaxNo());
            ci.setTaxBody(cid.getTaxBody());
            ci.setUser(user);
            this.addCompanyInfo(user, ci);
        }
        save(user);
    }

	public void updateBankInformation(User user, BankAccountInfoDto[] bankAccountInfo) {
        user.setBankAccounts(new HashSet<BankAccountInfo>());
        save(user);
        for(BankAccountInfoDto bad : bankAccountInfo) {
            BankAccountInfo bi = new BankAccountInfo();
            bi.setBankName(bad.getBankName());
            bi.setHolderName(bad.getHolderName());
            bi.setIban(bad.getIban());
            bi.setUser(user);
            this.addBankAccountInfo(user, bi);
        }
        
        /*if (bankAccountInfo.length > 0) {
            user.setIban(bankAccountInfo[0].getIban());
            user.setIbanBank(bankAccountInfo[0].getBankName());
        }
        */
        save(user);
	}

    public void updateSettings(User user, SettingsDto settings) {
        user.setTab1(settings.getTab1());
        user.setTab2(settings.getTab2());
        user.setTab3(settings.getTab3());
        save(user);
    }

    public List<User> getConnections(User user) {
        return userRepository.findAll();
    }

    public void updateLinks(User user, LinkDto[] links) {
        user.setLinks(new HashSet<Link>());
        save(user);
        for(LinkDto linkDto : links) {
            Link l = new Link();
            l.setValue(linkDto.getValue());
            l.setUser(user);
            this.addLink(user, l);
        }
    }

    public void increaseProfileVisitCount(User user) {
        user.setProfileVisits(user.getProfileVisits() + 1);
        this.save(user);
    }

    public List<User> getInsights() {
        return userRepository.findFirst10ByOrderByProfileVisitsDesc();
    }

    public void updatePhoto(User user, String imagePath) {
        user.setProfilePhoto(imagePath);
        this.save(user);
    }

    public void removePhoto(User user) {
        user.setProfilePhoto(thumbnail);
        this.save(user);
    }

    public void updateCompanyPhoto(User user, String fileName) {
        user.setCompanyLogoUrl(fileName);
        this.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> getAllUsers(int start, int size) {
        Pageable pagination = PageRequest.of(start, size);
        return userRepository.findAll(pagination);
    }

    public Page<User> getAllUsers(int start, int size, UserSearch search, String companyId) {
        Pageable pagination = PageRequest.of(start, size, Sort.by("id").descending());
        Company company = null;
        if(companyId != null && !companyId.trim().equals("")) {
            company = companyService.getCompanyById(companyId);
        }
        Specification<User> searchSpecification = searchLike("name", search.getName())
            .and(searchLike("surname", search.getSurname()))
            .and(searchLike("profileToken", search.getUrl())
            .and(searchLike("username", search.getEmail())));
        Specification<User> specification = null;
        if (companyId != null && !companyId.trim().equals("") && !companyId.equals("0")) {
            specification = findByCompany(company).and(searchSpecification);
        } else if (companyId != null && companyId.equals("0")) {
            specification = findVoilaCompanies().and(searchSpecification);
        } else {
            specification = searchSpecification;
        }

        if(search.getCompanies()!= null && !search.getCompanies().isEmpty()){
            specification.and(searchByCompanies(search.getCompanies()));
        }

        Page<User> users = userRepository.findAll(specification, pagination);
        return users;
    }

    public User createUser(String username, String password, String name, String surname, String token, String note) throws UserAlreadyExistException{
        User user = getUserByUsername(username);
        if (user != null)
            throw new UserAlreadyExistException();
        User newUser = new User(username, passwordEncoder.encode(password), name, surname, token, note);
        return userRepository.save(newUser);
    }

    public User createUser(String username, String password, String name, String surname, String token, String note, boolean locked, boolean photoUploadGranted, Company company) throws UserAlreadyExistException{
        User user = getUserByUsername(username);
        if (user != null)
            throw new UserAlreadyExistException();
        User newUser = new User(username, passwordEncoder.encode(password), name, surname, token, note, locked, photoUploadGranted, company);
        return userRepository.save(newUser);
    }

    public User updateUser(User user, String username, String password, String name, String surname, String token) {
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public void updateProfileToken(User user, String token) {
        user.setProfileToken(token);
        save(user);
    }

    public boolean isUserWithProfileTokenExist(String token) {
        User user = getByProfileToken(token);
        return user != null;
    }

    public Page<User> getAllUsers(int start, int size, String all) {
        Pageable pagination = PageRequest.of(start, size, Sort.by("id").descending());
        return userRepository.findByUsernameContainingOrProfileTokenContainingOrNameContainingOrSurnameContaining(all, all, all, all, pagination);
    }

    private Specification<User> findByCompany(Company company) {
        return(root, query, builder) -> {
            return builder.equal(root.get("company"), company);
        };
    }

    private Specification<User> findVoilaCompanies() {
        return(root, query, builder) -> {
            return builder.isNull(root.get("company"));
        };
    }

    private Specification<User> searchLike(String property, String needle) {
        return(root, query, builder) -> {
            return builder.like(root.get(property), "%" + needle + "%");
        };
    }

    private Specification<User> searchByCompanies(List<Company> companies) {
        return(root, query, builder) -> {
            return builder.in(root.get("company")).value(companies);
        };
    }

    public void resetUser(User user) {
        updateUserStatus(user, UserStatus.SETUP);
    }

    private void updateUserStatus(User user, UserStatus status) {
        user.setStatus(status);
        save(user);
    }

    public void disableUser(User user) {
        updateUserStatus(user, UserStatus.PASSIVE);
    }

    public void enableUser(User user) {
        updateUserStatus(user, UserStatus.ACTIVE);
    }

    public int countByCompany(Company c) {
        return userRepository.countByCompany(c);
    }
}
