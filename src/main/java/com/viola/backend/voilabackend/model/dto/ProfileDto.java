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

    public ProfileDto() {

    }

    public ProfileDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(user, UserDto.class);
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
        this.setPersonal(userDto);
        this.setCompanyInfo(companies);
        this.setContactInfo(contacts);
        this.setLinks(links);
        this.setBankAccountInfo(bankAccounts);
        this.setSocialMediaAccounts(socialMediaAccounts);
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

    public String jsonString() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        return jsonString;
    }
}
