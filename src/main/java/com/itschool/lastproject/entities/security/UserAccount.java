package com.itschool.lastproject.entities.security;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userAccountId;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String passwordConfirm;

    private String email;

    private String randomToken;

    @Singular
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "userAccount_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities;

    @Builder.Default
    private Boolean accountNonExpired = true;

    @Builder.Default
    private Boolean accountNonLocked = true;

    @Builder.Default
    private Boolean credentialsNonExpired = true;

    @Builder.Default
    private  Boolean enabled = false;

    public UserAccount(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public void addRole(Authority authority) {
        if(authorities==null){
            authorities = new HashSet<>();
        }
        authorities.add(authority);
    }

    public UserAccount(UserAccount userAccount) {
        this.firstName = userAccount.getFirstName();
        this.lastName = userAccount.getLastName();
        this.username = userAccount.getUsername();
        this.email = userAccount.getEmail();
        this.authorities = userAccount.getAuthorities();
        this.accountNonExpired = userAccount.getAccountNonExpired();
        this.accountNonLocked = userAccount.getAccountNonLocked();
        this.credentialsNonExpired = userAccount.getCredentialsNonExpired();
        this.enabled = userAccount.getEnabled();
    }
}
