package com.viola.backend.voilabackend.model.dto;

public class BankAccountInfoDto implements Comparable<BankAccountInfoDto>{
    private String bankName;
    private String holderName;
    private String iban;
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getHolderName() {
        return holderName;
    }
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
    public String getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }
    @Override
    public int compareTo(BankAccountInfoDto o) {
        if (this.getBankName().toUpperCase().equals(o.getBankName().toUpperCase())) {
            return this.getIban().compareTo(o.getIban());
        }
        return this.getBankName().toUpperCase().compareTo(o.getBankName().toUpperCase());
    }
}
