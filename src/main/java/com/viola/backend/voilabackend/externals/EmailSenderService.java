package com.viola.backend.voilabackend.externals;

import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class EmailSenderService {
    private final String DOMAINURL = "";
    private final String RESET_PATH = "";
    public boolean sendForgotPasswordEmail(String emailAddress, String token) {
        String resetLink = DOMAINURL + RESET_PATH + token;
        Email email = new Email(emailAddress, "Your password reset link.", resetLink);
        return send(email);
    }

    private boolean send(Email email) {
        return true;
    }
}
