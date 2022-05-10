package com.itschool.lastproject.services.email.impl;

import com.fasterxml.uuid.Generators;
import com.itschool.lastproject.entities.security.UserAccount;
import com.itschool.lastproject.services.email.RandomTokenService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomTokenServiceImpl implements RandomTokenService {


    @Override
    public String randomToken(UserAccount userAccount) {
        UUID nameBasedUUID = Generators.nameBasedGenerator().generate(userAccount.getUsername());
        return nameBasedUUID.toString();
    }
}
