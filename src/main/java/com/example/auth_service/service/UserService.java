package com.example.auth_service.service;

import com.example.auth_service.model.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap();

    public UserService(){
        users.put("admin", new User("admin", "password", Arrays.asList("ROLE_ADMIN", "ROLE_USER")));
        users.put("user", new User("user", "password", Arrays.asList("ROLE_USER")) ); // can't change list
    }

    public User findByUsername(String username) {
        return (User) users.get(username);
    }

    public boolean validateCredentials(String username, String password) {
        User user = findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
