package com.itschool.lastproject.controllers.security;

import com.itschool.lastproject.entities.security.UserAccount;
import com.itschool.lastproject.services.security.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserAccountService userAccountService;

    @GetMapping
    public String displayRegisterForm (Model model){

        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);

        return "security/register";
    }

    @PostMapping
    public String registerUser (@ModelAttribute("userAccount") @RequestBody UserAccount userAccount) {
        if(userAccount.getPassword().equalsIgnoreCase(userAccount.getPasswordConfirm())) {
            userAccountService.saveUser(userAccount);
            return "redirect:/cities";
        } else {
            return "security/register";
        }
    }


}
