package com.example.auth_service.config;

import com.example.auth_service.model.User;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

// CommandLineRunner: executes run() automatically
// at application startup after the Spring context
// is loaded
@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserService userService;

    public DatabaseInitializer (UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        // Check if the database is empty before adding demo users
        if (userRepository.count() == 0) {
            User admin = new User("admin", "admin@example.com", "password", "randomSalt",
                    LocalDateTime.now(), null, 1, List.of("ROLE_ADMIN", "ROLE_USER"));
            User user = new User("user", "user@example.com", "password", "randomSalt",
                    LocalDateTime.now(), null, 1, List.of("ROLE_USER"));

            userService.saveUser(admin);
            userService.saveUser(user);
        }
    }
}
