package com.itschool.lastproject.services.email.impl;

import com.itschool.lastproject.entities.security.UserAccount;
import com.itschool.lastproject.services.email.BodyBuilderService;
import org.springframework.stereotype.Service;

@Service
public class BodyBuilderServiceImpl implements BodyBuilderService {



    @Override
    public String emailBody(UserAccount userAccount) {

        String message = "Hello " + userAccount.getFirstName()
                + "\n In order to activate your account please access the following link:"
                + "http://localhost:8080/activation/" + userAccount.getRandomToken()
                + "\n\n Thank you for choosing The Sports Arena, have fun and don't stop playing!";

        return String.format("<b>%s</b>", message);
    }
}
