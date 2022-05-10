package com.itschool.lastproject.controllers.security;

import com.itschool.lastproject.entities.security.UserAccount;
import com.itschool.lastproject.repositories.security.UserAccountRepository;
import com.itschool.lastproject.services.security.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/activation")
public class ActivationController {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserAccountRepository userAccountRepository;


    @GetMapping(value = "/{randomToken}")
    public String activationForm(@PathVariable String randomToken, Model model) {
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccountService.findUserByRandomToken(randomToken));
        return "/security/activation";

    }

    @PostMapping(value = "/{randomToken}")
    public String activateUser(@ModelAttribute("userAccount")
                               @RequestBody UserAccount userAccount) {
        userAccountService.findUserByRandomToken(userAccount.getRandomToken()).setEnabled(true);
        userAccountRepository.save(userAccountService.findUserByRandomToken(userAccount.getRandomToken()));
        return "/security/activation-success";
    }
}
