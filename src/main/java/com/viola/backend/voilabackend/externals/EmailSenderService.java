package com.viola.backend.voilabackend.externals;

import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class EmailSenderService {

    private boolean send(Email email) {
        return true;
    }
}
