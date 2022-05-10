package com.itschool.lastproject.services.email;

import com.itschool.lastproject.entities.security.UserAccount;

public interface BodyBuilderService {

    String emailBody (UserAccount userAccount);
}
