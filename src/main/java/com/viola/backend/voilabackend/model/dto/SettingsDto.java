package com.viola.backend.voilabackend.model.dto;

import com.viola.backend.voilabackend.model.domain.User;

public class SettingsDto {
    private String tab1;
    private String tab2;
    private String tab3;
    private boolean photoUploadGranted;
    private boolean locked;

    public SettingsDto() {

    }

    public SettingsDto(User user) {
        this.tab1 = user.getTab1();
        this.tab2 = user.getTab2();
        this.tab3 = user.getTab3();
        this.photoUploadGranted = user.isPhotoUploadGranted();
        this.locked = user.isLocked();
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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
