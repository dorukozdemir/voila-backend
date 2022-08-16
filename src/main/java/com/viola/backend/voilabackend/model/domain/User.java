package com.viola.backend.voilabackend.model.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "cardvisits")
public class User implements UserDetails{

    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String bio;
    private Date created = new Date();
    private String resetPasswordToken;
    private Date resetPasswordTokenExpiry = new Date();
    private String profileToken = UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(0, 10);
    private SocialMediaAccounts socialMediaAccounts;
    private Set<ContactInfo> contactInfo;
    private Set<Link> links;
    private Set<CompanyInfo> companies;
    private Set<BankAccountInfo> bankAccounts;
    private String website1;
    private String website2;
    private String website3;
    private String website4;
    private String whatsapp;
    private String email1;
    private String email2;
    private String phone;
    private String phone2;
    private String phone3;
    private String companyName;
    private String iban;
    private String ibanBank;
    private int profileVisits = 0;
    private String profilePhoto = "/CardvisitUI/Dash/media/avatars/blank.png";

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String name, String surname, String token) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.profileToken = token;
    }

    @Id
    /*@SequenceGenerator(name="cardvisits_id_seq",
                   sequenceName="cardvisits_id_seq",
                   allocationSize=1) */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="email", unique=true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name="title")
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Column(name="created_at")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }  

    @Column(name="password_reset_token")
    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Transient
    public Date getResetPasswordTokenExpiry() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance(); 
        c.setTime(dt); 
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        this.resetPasswordTokenExpiry = dt;
        return resetPasswordTokenExpiry;
    }

    public void setResetPasswordTokenExpiry(Date resetPasswordTokenExpiry) {
        this.resetPasswordTokenExpiry = resetPasswordTokenExpiry;
    }
    
    @Column(name="url")
    public String getProfileToken() {
        return profileToken;
    }

    public void setProfileToken(String profileToken) {
        this.profileToken = profileToken;
    }

    @Embedded
    public SocialMediaAccounts getSocialMediaAccounts() {
        return socialMediaAccounts;
    }

    public void setSocialMediaAccounts(SocialMediaAccounts socialMediaAccounts) {
        this.socialMediaAccounts = socialMediaAccounts;
    }
    //@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Transient
    public Set<ContactInfo> getContactInfo() {
        this.contactInfo = new HashSet<ContactInfo>();
        ContactInfo ciEmail = new ContactInfo(ContactType.EMAIL, "" ,this.username);
        this.contactInfo.add(ciEmail);
        if (this.whatsapp!=null && !this.whatsapp.trim().equals("")) {
            ContactInfo ci = new ContactInfo(ContactType.WHATSAPP, "", this.whatsapp);
            this.contactInfo.add(ci);
        }
        if (this.email1 != null && !this.email1.trim().equals("")) {
            ContactInfo ci = new ContactInfo(ContactType.EMAIL, "", this.email1);
            this.contactInfo.add(ci);
        }
        if (this.email2 != null && !this.email2.trim().equals("")) {
            ContactInfo ci = new ContactInfo(ContactType.EMAIL, "", this.email2);
            this.contactInfo.add(ci);
        }
        if (this.phone != null && !this.phone.trim().equals("")) {
            ContactInfo ci = new ContactInfo(ContactType.PHONE, "", this.phone);
            this.contactInfo.add(ci);
        }
        if (this.phone2 != null && !this.phone2.trim().equals("")) {
            ContactInfo ci = new ContactInfo(ContactType.PHONE, "", this.phone2);
            this.contactInfo.add(ci);
        }
        if (this.phone3 != null && !this.phone3.trim().equals("")) {
            ContactInfo ci = new ContactInfo(ContactType.PHONE, "", this.phone3);
            this.contactInfo.add(ci);
        }
        return contactInfo;
    }

    public void setContactInfo(Set<ContactInfo> contactInfo) {
        this.contactInfo = contactInfo;
    }

    // @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Transient
    public Set<Link> getLinks() {
        this.links = new HashSet<Link>();
        if (this.website1 != null && !this.website1.trim().equals("anObject")) {
            Link link = new Link(this.website1);
            this.links.add(link);
        }
        if (this.website2 != null && !this.website2.trim().equals("anObject")) {
            Link link = new Link(this.website2);
            this.links.add(link);
        }
        if (this.website3 != null && !this.website3.trim().equals("anObject")) {
            Link link = new Link(this.website3);
            this.links.add(link);
        }
        if (this.website4 != null && !this.website4.trim().equals("anObject")) {
            Link link = new Link(this.website4);
            this.links.add(link);
        }
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }

    // @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Transient
    public Set<CompanyInfo> getCompanies() {
        this.companies = new HashSet<CompanyInfo>();
        CompanyInfo ci = new CompanyInfo(this.companyName, "", "", "");
        this.companies.add(ci);
        return companies;
    }

    public void setCompanies(Set<CompanyInfo> companies) {
        this.companies = companies;
    }

    //@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Transient
    public Set<BankAccountInfo> getBankAccounts() {
        this.bankAccounts = new HashSet<BankAccountInfo>();
        String holderName = "";
        if (this.name != null && !this.name.trim().equals("")) {
            holderName = this.name;
        }
        if (this.surname != null && !this.surname.trim().equals("")) {
            holderName = holderName + " " + this.surname;
        }
        holderName = holderName.trim();
        BankAccountInfo bai = new BankAccountInfo(this.ibanBank, holderName, this.iban);
        this.bankAccounts.add(bai);
        return bankAccounts;
    }

    public void setBankAccounts(Set<BankAccountInfo> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Transient
    public void updateSocialMediaAccounts(SocialMediaAccounts updatedSocialMediaAccounts) {
        this.getSocialMediaAccounts().setFacebook(updatedSocialMediaAccounts.getFacebook());
        this.getSocialMediaAccounts().setTwitter(updatedSocialMediaAccounts.getTwitter());
        this.getSocialMediaAccounts().setInstagram(updatedSocialMediaAccounts.getInstagram());
        this.getSocialMediaAccounts().setTiktok(updatedSocialMediaAccounts.getTiktok());
        this.getSocialMediaAccounts().setYoutube(updatedSocialMediaAccounts.getYoutube());
        this.getSocialMediaAccounts().setLinkedin(updatedSocialMediaAccounts.getLinkedin());
        this.getSocialMediaAccounts().setBehance(updatedSocialMediaAccounts.getBehance());
        this.getSocialMediaAccounts().setDribble(updatedSocialMediaAccounts.getDribble());
        this.getSocialMediaAccounts().setPinterest(updatedSocialMediaAccounts.getPinterest());
        this.getSocialMediaAccounts().setGithub(updatedSocialMediaAccounts.getGithub());
        this.getSocialMediaAccounts().setTelegram(updatedSocialMediaAccounts.getTelegram());
        this.getSocialMediaAccounts().setSnap(updatedSocialMediaAccounts.getSnap());
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		return authorities;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }

    public void addContactInfo(ContactInfo singleContactInfo) {
        if (this.contactInfo == null) {
            this.contactInfo = new LinkedHashSet<ContactInfo>();
        }
        //this.contactInfo.add(singleContactInfo);
        if(singleContactInfo.getContactType().equals(ContactType.WHATSAPP)) {
            this.whatsapp = singleContactInfo.getValue();
        } else if (singleContactInfo.getContactType().equals(ContactType.EMAIL)) {
            if(this.email1 == null || this.email1.trim().equals("")) {
                this.email1 = singleContactInfo.getValue();
            } else if(this.email2 == null || this.email2.trim().equals("")) {
                this.email2 = singleContactInfo.getValue();
            }
        } else if(singleContactInfo.getContactType().equals(ContactType.PHONE)) {
            if(this.phone == null || this.phone.trim().equals("")) {
                this.phone = singleContactInfo.getValue();
            } else if(this.phone2 == null || this.phone2.trim().equals("")) {
                this.phone2 = singleContactInfo.getValue();
            } else if(this.phone3 == null || this.phone3.trim().equals("")) {
                this.phone3 = singleContactInfo.getValue();
            }
        }
    }

    public void addLink(Link link) {
        if (this.links == null) {
            this.links = new LinkedHashSet<Link>();
        }
        if(this.website1 == null || this.website1.trim().equals("")) {
            this.setWebsite1(link.getValue());
        }
        else if(this.website2 == null || this.website2.trim().equals("")) {
            this.setWebsite2(link.getValue());
        }
        else if(this.website3 == null || this.website3.trim().equals("")) {
            this.setWebsite3(link.getValue());
        } else if(this.website4 == null || this.website4.trim().equals("")) {
            this.setWebsite4(link.getValue());
        }
        //this.links.add(link);
    }

    public void addCompanyInfo(CompanyInfo companyInfo) {
        if (this.companies == null) {
            this.companies = new LinkedHashSet<CompanyInfo>();
        }
        this.companyName = companyInfo.getName();
        //this.companies.add(companyInfo);
    }

    public void addBankAccountInfo(BankAccountInfo bankAccountInfo) {
        if (this.bankAccounts == null) {
            this.bankAccounts = new LinkedHashSet<BankAccountInfo>();
        }
        this.iban = bankAccountInfo.getIban();
        this.ibanBank = bankAccountInfo.getBankName();
    }

    @Column(name = "web_site_1")
    public String getWebsite1() {
        return website1;
    }

    public void setWebsite1(String website1) {
        this.website1 = website1;
    }

    @Column(name = "web_site_2")
    public String getWebsite2() {
        return website2;
    }

    public void setWebsite2(String website2) {
        this.website2 = website2;
    }

    @Column(name = "web_site_3")
    public String getWebsite3() {
        return website3;
    }

    public void setWebsite3(String website3) {
        this.website3 = website3;
    }

    @Column(name = "web_site_4")
    public String getWebsite4() {
        return website4;
    }

    public void setWebsite4(String website4) {
        this.website4 = website4;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getIbanBank() {
        return ibanBank;
    }

    public void setIbanBank(String ibanBank) {
        this.ibanBank = ibanBank;
    }

    @Column(name="profille_visits")
    public int getProfileVisits() {
        return profileVisits;
    }

    public void setProfileVisits(int profileVisits) {
        this.profileVisits = profileVisits;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof User) {
            if (((User) obj).getId().equals(getId())) {
                return true;
            }
        }

        return false;
    }

    @Transient
    public String getFullName() {
        String fullName = "";
        if(this.name != null && !this.name.trim().equals("")) {
            fullName = this.name;
        } if(this.surname != null && !this.surname.trim().equals("")) {
            fullName = fullName + " " + this.surname;
        }
        return fullName;
    }

    @Override
    public String toString() {
        return this.getFullName();
    }
}