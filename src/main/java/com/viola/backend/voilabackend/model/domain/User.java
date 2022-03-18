package com.viola.backend.voilabackend.model.domain;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "v_users_t")
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
    private String profileToken = UUID.randomUUID().toString();
    private SocialMediaAccounts socialMediaAccounts;
    private Set<ContactInfo> contactInfo;
    private Set<Link> links;
    private Set<CompanyInfo> companies;
    private Set<BankAccountInfo> bankAccounts;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }  

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public Date getResetPasswordTokenExpiry() {
        return resetPasswordTokenExpiry;
    }

    public void setResetPasswordTokenExpiry(Date resetPasswordTokenExpiry) {
        this.resetPasswordTokenExpiry = resetPasswordTokenExpiry;
    }
    
    public String getProfileToken() {
        return profileToken;
    }

    public void setProfileToken(String profileToken) {
        this.profileToken = profileToken;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
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
		authorities.add(new SimpleGrantedAuthority("Kullanıcı"));
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
}