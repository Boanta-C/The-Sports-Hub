package com.itschool.lastproject.repositories.security;

import com.itschool.lastproject.entities.security.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<UserAccount, Long> {
}
