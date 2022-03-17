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

    public ProfileDto() {

    }

    public ProfileDto(User otherUser) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(otherUser, UserDto.class);
        CompanyInfoDto[] companies = modelMapper.map(otherUser.getCompanies(), CompanyInfoDto[].class);
        Arrays.sort(companies);
        ContactInfoDto[] contacts = modelMapper.map(otherUser.getContactInfo(), ContactInfoDto[].class);
        Arrays.sort(contacts);
        LinkDto[] links = modelMapper.map(otherUser.getLinks(), LinkDto[].class);
        Arrays.sort(links);
        BankAccountInfoDto[] bankAccounts = modelMapper.map(otherUser.getBankAccounts(), BankAccountInfoDto[].class);
        Arrays.sort(bankAccounts);
        this.setPersonal(userDto);
        this.setCompanyInfo(companies);
        this.setContactInfo(contacts);
        this.setLinks(links);
        this.setBankAccountInfo(bankAccounts);
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

    public String jsonString() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        return jsonString;
    }
}
