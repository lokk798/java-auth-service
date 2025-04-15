package com.example.auth_service.controller;

import com.example.auth_service.config.JwtUtil;
import com.example.auth_service.model.User;
import com.example.auth_service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // contains the login logic
    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest ){
        //validate credentials
        if(userService.validateCredentials(loginRequest.getUsername(), loginRequest.getPassword())){
            User user = userService.findByUsername(loginRequest.getUsername());
            String token = jwtUtil.generateToken(user);
            System.out.println("Token: " + token);
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.badRequest().body("Invalid username or password");
    }

    @PostMapping("auth/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest logoutRequest){
        userService.recordLogout(logoutRequest.getUsername());
        return ResponseEntity.ok("Logout successful");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class LoginRequest{
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthResponse{
        private String token;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class LogoutRequest{
        private String username;
    }

}
