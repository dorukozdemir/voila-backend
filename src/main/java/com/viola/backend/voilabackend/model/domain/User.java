package com.viola.backend.voilabackend.model.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
    private String note;
    private Company company;
    private String ownerCompany;
    private UserStatus status = UserStatus.ACTIVE;
    private boolean locked = false;
    private String companyLogoUrl;
    private String tab1 = "profile";
    private String tab2= "social";
    private String tab3= "company";
    private boolean photoUploadGranted = false;

    public String getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String name, String surname, String token, String note) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.profileToken = token;
        this.note = note;
    }

    public User(String username, String password, String name, String surname, String token, String note, boolean locked, boolean photoUploadGranted, Company company) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.profileToken = token;
        this.note = note;
        this.locked = locked;
        this.photoUploadGranted = photoUploadGranted;
        this.company = company;
    }

    @Id
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    public SocialMediaAccounts getSocialMediaAccounts() {
        return socialMediaAccounts;
    }

    public void setSocialMediaAccounts(SocialMediaAccounts socialMediaAccounts) {
        this.socialMediaAccounts = socialMediaAccounts;
    }
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<ContactInfo> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(Set<ContactInfo> contactInfo) {
        this.contactInfo = contactInfo;
    }

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<CompanyInfo> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<CompanyInfo> companies) {
        this.companies = companies;
    }

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@Transient
    public Set<BankAccountInfo> getBankAccounts() {
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
        this.contactInfo.add(singleContactInfo);
    }

    public void addLink(Link link) {
        if (this.links == null) {
            this.links = new LinkedHashSet<Link>();
        }
        this.links.add(link);
    }

    public void addCompanyInfo(CompanyInfo companyInfo) {
        if (this.companies == null) {
            this.companies = new LinkedHashSet<CompanyInfo>();
        }
        this.companies.add(companyInfo);
    }

    public void addBankAccountInfo(BankAccountInfo bankAccountInfo) {
        if (this.bankAccounts == null) {
            this.bankAccounts = new LinkedHashSet<BankAccountInfo>();
        }
        this.bankAccounts.add(bankAccountInfo);
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @ManyToOne
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Column(name = "is_cardvisit_url_lock")
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Column(name="company_logo_path")
    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }

    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
    }

    public String getTab1() {
        return tab1;
    }

    public void setTab1(String tab1) {
        this.tab1 = tab1;
    }

    public String getTab2() {
        return tab2;
    }

    public void setTab2(String tab2) {
        this.tab2 = tab2;
    }

    public String getTab3() {
        return tab3;
    }

    public void setTab3(String tab3) {
        this.tab3 = tab3;
    }

    public boolean isPhotoUploadGranted() {
        return photoUploadGranted;
    }

    public void setPhotoUploadGranted(boolean photoUploadGranted) {
        this.photoUploadGranted = photoUploadGranted;
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