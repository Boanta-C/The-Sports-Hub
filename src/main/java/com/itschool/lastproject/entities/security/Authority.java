package com.itschool.lastproject.entities.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authorityId;

    private String role;

    @ManyToMany(mappedBy = "authorities")
    private Set<UserAccount> userAccounts;

    public Authority(String role) {
        this.role = role;
    }

}
