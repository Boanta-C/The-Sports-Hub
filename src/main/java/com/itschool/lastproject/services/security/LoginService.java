package com.itschool.lastproject.services.security;

import com.itschool.lastproject.repositories.security.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;
}
