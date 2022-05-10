package com.itschool.lastproject.entities.locations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


public class AdminAccount {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long adminId;

    private String adminName;

    private String adminEmail;

    private String role;

}
