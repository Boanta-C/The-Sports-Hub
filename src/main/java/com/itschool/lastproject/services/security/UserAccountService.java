package com.itschool.lastproject.services.security;

import com.itschool.lastproject.entities.security.UserAccount;
import com.itschool.lastproject.repositories.security.AuthorityRepository;
import com.itschool.lastproject.repositories.security.UserAccountRepository;
import com.itschool.lastproject.services.email.BodyBuilderService;
import com.itschool.lastproject.services.email.EmailSender;
import com.itschool.lastproject.services.email.RandomTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    RandomTokenService randomTokenService;

    @Autowired
    EmailSender emailSender;

    @Autowired
    BodyBuilderService bodyBuilderService;

    @Autowired
    AuthorityRepository authorityRepository;

    public UserAccount saveUser(UserAccount userAccount) {
        UserAccount user = new UserAccount(userAccount);

        user.setPassword(new BCryptPasswordEncoder().encode(userAccount.getPassword()));
        user.setPasswordConfirm(new BCryptPasswordEncoder().encode(userAccount.getPasswordConfirm()));
        user.setRandomToken(randomTokenService.randomToken(userAccount));
        user.setAuthorities(Set.of(authorityRepository.getByRole("USER")));
        emailSender.sendEmail(user.getEmail(), "Activate your account", bodyBuilderService.emailBody(user));

        return userAccountRepository.save(user);
    }

    public UserAccount findUserByRandomToken(String randomTokenUser) {
        return userAccountRepository.findByRandomToken(randomTokenUser);
    }
}
