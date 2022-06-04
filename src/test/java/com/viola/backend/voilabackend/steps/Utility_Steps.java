package com.viola.backend.voilabackend.steps;

import com.viola.backend.voilabackend.externals.EmailSenderService;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Utility_Steps {
    private String email;

    @Autowired
    private EmailSenderService emailSenderService;

    @When("eposta adresi {string} olarak girildiğinde")
    public void eposta_adresi_olarak_girildiğinde(String email) {
        this.email = email;
    }
    @Then("eposta gönderilecek")
    public void eposta_gönderilecek() {
        emailSenderService.sendSimpleMessage(this.email, "this is the subject", "this is the text");
    }
}
