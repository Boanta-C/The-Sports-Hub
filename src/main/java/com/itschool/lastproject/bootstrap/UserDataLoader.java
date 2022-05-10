package com.itschool.lastproject.bootstrap;

import com.itschool.lastproject.repositories.security.AuthorityRepository;
import com.itschool.lastproject.repositories.security.UserAccountRepository;
import com.itschool.lastproject.entities.security.Authority;
import com.itschool.lastproject.entities.security.UserAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDataLoader implements CommandLineRunner {


    private final AuthorityRepository authorityRepository;
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    private void loadSecurityData() {

        Authority owner = authorityRepository.save(Authority.builder().role("OWNER").build());
        Authority admin = authorityRepository.save(Authority.builder().role("ADMIN").build());
        Authority moderator = authorityRepository.save(Authority.builder().role("MODERATOR").build());
        Authority user = authorityRepository.save(Authority.builder().role("USER").build());

        userAccountRepository.save(UserAccount.builder()
                .username("cristiB")
                .password(passwordEncoder.encode("qqwweerr1"))
                .email("cristi.boanta0@gmail.com")
                .firstName("Cristi")
                .lastName("Boanta")
                .authority(owner)
                .authority(moderator)
                .authority(admin)
                .enabled(true)
                .build());

        userAccountRepository.save(UserAccount.builder()
                .username("lucianT")
                .password(passwordEncoder.encode("123456789"))
                .email("ticoiu.lucian@gmail.com")
                .firstName("Lucian")
                .lastName("Ticoiu")
                .authority(admin)
                .enabled(true)
                .build());

        userAccountRepository.save(UserAccount.builder()
                .username("andrewP")
                .password(passwordEncoder.encode("qweqweqwe"))
                .email("andrewPeterson@gmail.com")
                .firstName("Andrew")
                .lastName("Peterson")
                .authority(moderator)
                .enabled(true)
                .build());

        userAccountRepository.save(UserAccount.builder()
                .username("robertD")
                .password(passwordEncoder.encode("123123123"))
                .email("robert.d@gmail.com")
                .firstName("Robert")
                .lastName("Dickens")
                .authority(user)
                .enabled(true)
                .build());

    }


    @Override
    public void run(String... args) throws Exception {
        if (authorityRepository.count() == 0) {
            loadSecurityData();
        }


    }


}
