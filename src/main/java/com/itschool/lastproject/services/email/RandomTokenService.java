package com.itschool.lastproject.services.email;

import com.itschool.lastproject.entities.security.UserAccount;

public interface RandomTokenService {
    String randomToken(UserAccount userAccount);
}
