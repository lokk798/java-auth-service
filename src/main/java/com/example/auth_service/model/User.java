package com.example.auth_service.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private List roles;
}

//@Entity
//@Data // setters, getters, toString(), equals(), ...
//@AllArgsConstructor // constructor with all fields
//@NoArgsConstructor // no-argument constructor
//public class User {
//    @Id
//    private String username;
//    private String password;
//    private String email;
//    private String salt;
//    private LocalDateTime lastLogin;
//    private LocalDateTime lastLogout;
//    private int loginCount;
//
//    // store the collection in a separate table
//    // linked to the main one
//    // when you load the user immediately
//    // load the roles with 'FetchType.EAGER'
//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<String> roles;
//}
