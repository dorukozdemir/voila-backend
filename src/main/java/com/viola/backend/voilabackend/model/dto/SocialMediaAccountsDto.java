package com.viola.backend.voilabackend.model.dto;

public class SocialMediaAccountsDto {
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

    public SocialMediaAccountsDto() {

    }

    public SocialMediaAccountsDto(String facebook, String twitter, String instagram, String tiktok, String youtube,
            String linkedin, String behance, String dribble, String pinterest, String github, String telegram,
            String snap) {
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.tiktok = tiktok;
        this.youtube = youtube;
        this.linkedin = linkedin;
        this.behance = behance;
        this.dribble = dribble;
        this.pinterest = pinterest;
        this.github = github;
        this.telegram = telegram;
        this.snap = snap;
    }

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
    public String getSnap() {
        return snap;
    }
    public void setSnap(String snap) {
        this.snap = snap;
    }

    
}
