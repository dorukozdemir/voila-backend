package com.viola.backend.voilabackend.model.dto;

public class ContactInfoDto implements Comparable<ContactInfoDto> {
    private String contactType;
    private String extension;
    private String value;
    
    public String getContactType() {
        return contactType;
    }
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public int compareTo(ContactInfoDto otherContactInfo) {
		String compareName = ((ContactInfoDto) otherContactInfo).getContactType().toUpperCase();
		if (compareName.equals(this.getContactType().toUpperCase())) {
            return this.getValue().compareTo(otherContactInfo.getValue());
        }
        return compareName.compareTo(this.getContactType().toUpperCase());
	}   
}
