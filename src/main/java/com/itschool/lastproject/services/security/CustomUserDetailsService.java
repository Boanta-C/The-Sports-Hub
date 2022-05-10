package com.itschool.lastproject.services.security;

import com.itschool.lastproject.repositories.security.UserAccountRepository;
import com.itschool.lastproject.entities.security.Authority;
import com.itschool.lastproject.entities.security.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Transactional //To have an active hibernate transaction, so we can go and fetch the authorities.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAccount userAccount = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

        return new User(   //Spring Security User
                userAccount.getUsername(), 
                userAccount.getPassword(),
                userAccount.getEnabled(),
                userAccount.getAccountNonExpired(),
                userAccount.getCredentialsNonExpired(),
                userAccount.getAccountNonLocked(),
                convertToSpringAuthorities(userAccount.getAuthorities()
                ));

    }

    private Collection<? extends GrantedAuthority> convertToSpringAuthorities(Set<Authority> authorities) {

        if (authorities != null && authorities.size() > 0) {
            return authorities.stream()
                    .map(Authority::getRole)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        } else {
            return new HashSet<>();
        }

    }
}
