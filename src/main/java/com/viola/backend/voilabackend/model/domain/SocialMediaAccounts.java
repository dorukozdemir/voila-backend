package com.viola.backend.voilabackend.model.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.viola.backend.voilabackend.model.dto.SocialMediaAccountsDto;

@Embeddable
public class SocialMediaAccounts {
    
    private String facebook;
    private String twitter;
    private String instagram;
    private String tiktok;
    private String youtube;
    private String linkedin;
    private String behance;
    private String dribble;
    private String pinterest;
    private String github;
    private String telegram;
    private String snap;


    public String getFacebook() {
        return facebook;
    }
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
    public String getTwitter() {
        return twitter;
    }
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
    public String getInstagram() {
        return instagram;
    }
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
    public String getTiktok() {
        return tiktok;
    }
    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }
    public String getYoutube() {
        return youtube;
    }
    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
    public String getLinkedin() {
        return linkedin;
    }
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
    public String getBehance() {
        return behance;
    }
    public void setBehance(String behance) {
        this.behance = behance;
    }
    @Column(name="dribbble")
    public String getDribble() {
        return dribble;
    }
    public void setDribble(String dribble) {
        this.dribble = dribble;
    }
    public String getPinterest() {
        return pinterest;
    }
    public void setPinterest(String pinterest) {
        this.pinterest = pinterest;
    }
    public String getGithub() {
        return github;
    }
    public void setGithub(String github) {
        this.github = github;
    }
    public String getTelegram() {
        return telegram;
    }
    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }
    @Column(name="snapchat")
    public String getSnap() {
        return snap;
    }
    public void setSnap(String snap) {
        this.snap = snap;
    }
    public void copyFromDto(SocialMediaAccountsDto socialMediaAccountsDto) {
        this.facebook = socialMediaAccountsDto.getFacebook();
        this.twitter = socialMediaAccountsDto.getTwitter();
        this.instagram = socialMediaAccountsDto.getInstagram();
        this.tiktok = socialMediaAccountsDto.getTiktok();
        this.youtube = socialMediaAccountsDto.getYoutube();
        this.linkedin = socialMediaAccountsDto.getLinkedin();
        this.behance = socialMediaAccountsDto.getBehance();
        this.dribble = socialMediaAccountsDto.getDribble();
        this.pinterest = socialMediaAccountsDto.getPinterest();
        this.github = socialMediaAccountsDto.getGithub();
        this.telegram = socialMediaAccountsDto.getTelegram();
        this.snap = socialMediaAccountsDto.getSnap();
    }

    
}
