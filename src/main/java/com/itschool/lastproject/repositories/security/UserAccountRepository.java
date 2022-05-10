package com.itschool.lastproject.repositories.security;

import com.itschool.lastproject.entities.security.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsername (String username);

    UserAccount findByRandomToken(String randomTokenUser);
}
