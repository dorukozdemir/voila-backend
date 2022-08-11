package com.viola.backend.voilabackend.model.dto;

import com.google.gson.Gson;

public class StatisticsDto {
    private int companyCount = 0;
    private int totalCardvisits = 0;
    private int totalMobileUsers = 0;
    private int setUsers = 0;
    
    public int getCompanyCount() {
        return companyCount;
    }
    public void setCompanyCount(int companyCount) {
        this.companyCount = companyCount;
    }
    public int getTotalCardvisits() {
        return totalCardvisits;
    }
    public void setTotalCardvisits(int totalCardvisits) {
        this.totalCardvisits = totalCardvisits;
    }
    public int getTotalMobileUsers() {
        return totalMobileUsers;
    }
    public void setTotalMobileUsers(int totalMobileUsers) {
        this.totalMobileUsers = totalMobileUsers;
    }
    public int getSetUsers() {
        return setUsers;
    }
    public void setSetUsers(int setUsers) {
        this.setUsers = setUsers;
    }

    public String jsonString() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        return jsonString;
    }
}
