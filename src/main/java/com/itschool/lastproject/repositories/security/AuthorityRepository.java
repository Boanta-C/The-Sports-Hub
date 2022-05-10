package com.itschool.lastproject.repositories.security;

import com.itschool.lastproject.entities.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority getByRole (String role);

}
