package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import com.viola.backend.voilabackend.exceptions.UserAlreadyExistException;
import com.viola.backend.voilabackend.model.domain.BankAccountInfo;
import com.viola.backend.voilabackend.model.domain.CompanyInfo;
import com.viola.backend.voilabackend.model.domain.Connection;
import com.viola.backend.voilabackend.model.domain.ContactInfo;
import com.viola.backend.voilabackend.model.domain.ContactType;
import com.viola.backend.voilabackend.model.domain.Link;
import com.viola.backend.voilabackend.model.domain.SocialMediaAccounts;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.model.dto.BankAccountInfoDto;
import com.viola.backend.voilabackend.model.dto.CompanyInfoDto;
import com.viola.backend.voilabackend.model.dto.ContactInfoDto;
import com.viola.backend.voilabackend.model.dto.LinkDto;
import com.viola.backend.voilabackend.model.dto.SocialMediaAccountsDto;
import com.viola.backend.voilabackend.model.dto.UserDto;
import com.viola.backend.voilabackend.repository.UserRepository;
import com.viola.backend.voilabackend.security.VoilaPasswordEncoder;

@Service("userService")
public class UserService {
    @Autowired
	private UserRepository userRepository;

    @Autowired
    private ConnectionService connectionService;

    private final int RESETPASSWORDTOKENDURATION = 15;

    private final VoilaPasswordEncoder passwordEncoder = new VoilaPasswordEncoder();
    
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
        //link.setUser(user);
        user.addLink(link);
        save(user);
    }

    @Transactional
    public void addCompanyInfo(User user, CompanyInfo companyInfo) {
        /*companyInfo.setUser(user);*/
        user.addCompanyInfo(companyInfo);
        save(user);
    }

    public void addBankAccountInfo(User user, BankAccountInfo bankAccountInfo) {
        //bankAccountInfo.setUser(user);
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
        Set<String> emails = new HashSet<String>();
        Set<String> phones = new HashSet<String>();
        for(ContactInfoDto contactInfoDto : contactInfo) {
            /*ContactInfo ci = new ContactInfo();
            ci.setContactType(ContactType.toContactType(contactInfoDto.getContactType()));
            ci.setExtension(contactInfoDto.getExtension());
            ci.setValue(contactInfoDto.getValue());
            ci.setUser(user);
            this.addContactInfo(user, ci);
            */
            if (ContactType.toContactType(contactInfoDto.getContactType()).equals(ContactType.EMAIL)) {
                if(!contactInfoDto.getValue().equals(user.getUsername())) {
                    emails.add(contactInfoDto.getValue());
                }
            }
            if (ContactType.toContactType(contactInfoDto.getContactType()).equals(ContactType.PHONE)) {
                phones.add(contactInfoDto.getValue());
            }
            if (ContactType.toContactType(contactInfoDto.getContactType()).equals(ContactType.WHATSAPP)) {
                user.setWhatsapp(contactInfoDto.getValue());
            }
        }
        if(emails.size() == 0) {
            user.setEmail1("");
            user.setEmail2("");
        } else if (emails.size() == 1) {
            user.setEmail1((String)emails.toArray()[0]);
            user.setEmail2("");
        } else if (emails.size() == 2) {
            user.setEmail1((String)emails.toArray()[0]);
            user.setEmail2((String)emails.toArray()[1]);
        }

        if(phones.size() == 0) {
            user.setPhone("");
            user.setPhone2("");
            user.setPhone3("");
        } else if (phones.size() == 1) {
            user.setPhone((String)phones.toArray()[0]);
            user.setPhone2("");
            user.setPhone3("");
        } else if (phones.size() == 2) {
            user.setPhone((String)phones.toArray()[0]);
            user.setPhone2((String)phones.toArray()[1]);
            user.setPhone3("");
        } else if (phones.size() == 3) {
            user.setPhone((String)phones.toArray()[0]);
            user.setPhone2((String)phones.toArray()[1]);
            user.setPhone3((String)phones.toArray()[2]);
        }

        save(user);
        
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

    public List<User> getConnections(User user) {
        return userRepository.findAll();
    }

    public void updateLinks(User user, LinkDto[] links) {
        if (links.length == 1) {
            user.setWebsite1(links[0].getValue());
        }
        else if (links.length == 2) {
            user.setWebsite1(links[0].getValue());
            user.setWebsite2(links[1].getValue());
        } else if (links.length == 3)  {
            user.setWebsite1(links[0].getValue());
            user.setWebsite2(links[1].getValue());
            user.setWebsite3(links[2].getValue());
        } else if (links.length == 4) {
            user.setWebsite1(links[0].getValue());
            user.setWebsite2(links[1].getValue());
            user.setWebsite3(links[2].getValue());
            user.setWebsite4(links[3].getValue());
        }
        this.save(user);
    }
}
