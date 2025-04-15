package com.example.auth_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "app_user")
@Data // setters, getters, toString(), equals(), ...
@AllArgsConstructor // constructor with all fields
@NoArgsConstructor // no-argument constructor
public class User {
    @Id
    private String username;
    private String password;
    private String email;
    private String salt;
    private LocalDateTime lastLogin;
    private LocalDateTime lastLogout;
    private int loginCount;

    // store the collection in a separate table
    // linked to the main one
    // when you load the user immediately
    // load the roles with 'FetchType.EAGER'
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
}
