package com.viola.backend.voilabackend.model.dto;

import com.viola.backend.voilabackend.model.domain.User;

public class UserDto {
    private String name;
    private String surname;
    private String bio;
    private String profileToken;
    private int profileVisits;
    private int profileSaves;
    private String profilePhoto;
    private boolean hasCompanyPhoto = false;
    private String companyPhotoUrl;
    private boolean hasCTA = false;
    private String ctaLink;
    private String ctaSentence;
    private String companyLogoUrl;
    private boolean photoUploadGranted;

    public UserDto() {

    }

    public UserDto(String name, String surname, String bio) {
        this.name = name;
        this.surname = surname;
        this.bio = bio;
    }
    
    public UserDto(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.bio = user.getBio();
        this.profileToken = user.getProfileToken();
        this.profileVisits = user.getProfileVisits();
        this.profileSaves = user.getProfileSaves();
        this.profilePhoto = user.getProfilePhoto();
        this.companyLogoUrl = user.getCompanyLogoUrl();
        this.photoUploadGranted = user.isPhotoUploadGranted();
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

    public String getProfileToken() {
        return profileToken;
    }

    public void setProfileToken(String profileToken) {
        this.profileToken = profileToken;
    }

    public int getProfileVisits() {
        return profileVisits;
    }

    public void setProfileVisits(int profileVisits) {
        this.profileVisits = profileVisits;
    }

    public int getProfileSaves() {
        return profileSaves;
    }

    public void setProfileSaves(int profileSaves) {
        this.profileSaves = profileSaves;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public boolean isHasCompanyPhoto() {
        return hasCompanyPhoto;
    }

    public void setHasCompanyPhoto(boolean hasCompanyPhoto) {
        this.hasCompanyPhoto = hasCompanyPhoto;
    }

    public String getCompanyPhotoUrl() {
        return companyPhotoUrl;
    }

    public void setCompanyPhotoUrl(String companyPhotoUrl) {
        this.companyPhotoUrl = companyPhotoUrl;
    }

    public boolean isHasCTA() {
        return hasCTA;
    }

    public void setHasCTA(boolean hasCTA) {
        this.hasCTA = hasCTA;
    }

    public String getCtaLink() {
        return ctaLink;
    }

    public void setCtaLink(String ctaLink) {
        this.ctaLink = ctaLink;
    }

    public String getCtaSentence() {
        return ctaSentence;
    }

    public void setCtaSentence(String ctaSentence) {
        this.ctaSentence = ctaSentence;
    }

    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }

    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
    }

    public boolean isPhotoUploadGranted() {
        return photoUploadGranted;
    }

    public void setPhotoUploadGranted(boolean photoUploadGranted) {
        this.photoUploadGranted = photoUploadGranted;
    }
}

