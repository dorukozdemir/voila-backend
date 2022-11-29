package com.viola.backend.voilabackend.model.dto;

import java.util.Arrays;

import com.google.gson.Gson;
import com.viola.backend.voilabackend.model.domain.User;

import org.modelmapper.ModelMapper;

public class ProfileDto {
    private UserDto personal;
    private CompanyInfoDto[] companyInfo;
    private ContactInfoDto[] contactInfo;
    private LinkDto[] links;
    private BankAccountInfoDto[] bankAccountInfo;
    private SocialMediaAccountsDto socialMediaAccounts;
    private SettingsDto settings;

    public ProfileDto() {

    }

    public ProfileDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        if(user.getOwnerCompany() != null && !user.getOwnerCompany().trim().equals("")) {
            if(user.getOwnerCompany().equals("cimtas")) {
                userDto.setHasCompanyPhoto(true);
                userDto.setCompanyPhotoUrl("https://voilacard.com/logos/cimtas-logo.png");
            } else if(user.getOwnerCompany().equals("ticimax")) {
                userDto.setHasCTA(true);
                userDto.setCtaLink("https://www.ticimax.com/");
                userDto.setCtaSentence("Ticimax ile tanışın");
            }
        }
        CompanyInfoDto[] companies = modelMapper.map(user.getCompanies(), CompanyInfoDto[].class);
        Arrays.sort(companies);
        ContactInfoDto[] contacts = modelMapper.map(user.getContactInfo(), ContactInfoDto[].class);
        Arrays.sort(contacts);
        LinkDto[] links = modelMapper.map(user.getLinks(), LinkDto[].class);
        Arrays.sort(links);
        BankAccountInfoDto[] bankAccounts = modelMapper.map(user.getBankAccounts(), BankAccountInfoDto[].class);
        Arrays.sort(bankAccounts);
        SocialMediaAccountsDto socialMediaAccounts = new SocialMediaAccountsDto();
        if(user.getSocialMediaAccounts() != null) {
            socialMediaAccounts = modelMapper.map(user.getSocialMediaAccounts(), SocialMediaAccountsDto.class);
        }
        SettingsDto settings = new SettingsDto(user);
        this.setPersonal(userDto);
        this.setCompanyInfo(companies);
        this.setContactInfo(contacts);
        this.setLinks(links);
        this.setBankAccountInfo(bankAccounts);
        this.setSocialMediaAccounts(socialMediaAccounts);
        this.setSettings(settings);
    }

    public UserDto getPersonal() {
        return personal;
    }

    public void setPersonal(UserDto personal) {
        this.personal = personal;
    }

    public CompanyInfoDto[] getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfoDto[] companyInfo) {
        this.companyInfo = companyInfo;
    }

    public ContactInfoDto[] getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfoDto[] contactInfo) {
        this.contactInfo = contactInfo;
    }

    public LinkDto[] getLinks() {
        return links;
    }

    public void setLinks(LinkDto[] links) {
        this.links = links;
    }

    public BankAccountInfoDto[] getBankAccountInfo() {
        return bankAccountInfo;
    }

    public void setBankAccountInfo(BankAccountInfoDto[] bankAccountInfo) {
        this.bankAccountInfo = bankAccountInfo;
    }

    public SocialMediaAccountsDto getSocialMediaAccounts() {
        return socialMediaAccounts;
    }

    public void setSocialMediaAccounts(SocialMediaAccountsDto socialMediaAccounts) {
        this.socialMediaAccounts = socialMediaAccounts;
    }

    public SettingsDto getSettings() {
        return settings;
    }

    public void setSettings(SettingsDto settings) {
        this.settings = settings;
    }

    public String jsonString() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        return jsonString;
    }
}
