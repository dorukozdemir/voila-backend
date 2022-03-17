package com.viola.backend.voilabackend.model.dto;

public class LinkDto implements Comparable<LinkDto>{
    public String value;

    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(LinkDto o) {
        return this.getValue().toUpperCase().compareTo(o.getValue().toUpperCase());
    }

    
}
