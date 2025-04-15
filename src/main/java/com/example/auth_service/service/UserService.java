package com.example.auth_service.service;

import com.example.auth_service.model.User;
import com.example.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean validateCredentials(String username, String password) {
        User user = findByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            // update login metadata
            user.setLastLogin(LocalDateTime.now());
            user.setLoginCount(user.getLoginCount() + 1);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public void recordLogout(String username){
        User user = findByUsername(username);
        if(user != null){
            user.setLastLogout(LocalDateTime.now());
            userRepository.save(user);
        }
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}
