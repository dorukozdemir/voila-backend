package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.viola.backend.voilabackend.model.domain.CompanyInfo;
import com.viola.backend.voilabackend.model.domain.ContactInfo;
import com.viola.backend.voilabackend.model.domain.ContactType;
import com.viola.backend.voilabackend.model.domain.Link;
import com.viola.backend.voilabackend.model.domain.SocialMediaAccounts;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.dto.BankAccountInfoDto;
import com.viola.backend.voilabackend.model.dto.CompanyInfoDto;
import com.viola.backend.voilabackend.model.dto.ContactInfoDto;
import com.viola.backend.voilabackend.model.dto.SocialMediaAccountsDto;
import com.viola.backend.voilabackend.model.dto.UserDto;
import com.viola.backend.voilabackend.repository.UserRepository;

@Service("userService")
public class UserService {
    @Autowired
	UserRepository userRepository;

    private final int RESETPASSWORDTOKENDURATION = 15;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
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
        user.setResetPasswordToken(UUID.randomUUID().toString());
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
            user.updateSocialMediaAccounts(socialMediaAccounts);
            save(user);
        }
    }

    public void updateSocialMediaAccounts(User user, SocialMediaAccountsDto socialMediaAccountsDto) {
        if (user.getSocialMediaAccounts() == null) {
            SocialMediaAccounts socialMediaAccounts = new SocialMediaAccounts();
            socialMediaAccounts.copyFromDto(socialMediaAccountsDto);
            socialMediaAccounts.setUser(user);
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
	}

    public List<User> getConnections(User user) {
        return userRepository.findAll();
    }
}
