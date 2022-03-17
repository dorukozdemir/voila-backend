package com.viola.backend.voilabackend.dto;


public class CompanyInfoDto implements Comparable<CompanyInfoDto>{
    private String name;
    private String address;
    private String taxNo;
    private String taxBody;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTaxNo() {
        return taxNo;
    }
    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }
    public String getTaxBody() {
        return taxBody;
    }
    public void setTaxBody(String taxBody) {
        this.taxBody = taxBody;
    }
	
    public int compareTo(CompanyInfoDto otherCompanyInfo) {
		String compareName = ((CompanyInfoDto) otherCompanyInfo).getName().toUpperCase();
		return this.getName().toUpperCase().compareTo(compareName);
	}    
}
