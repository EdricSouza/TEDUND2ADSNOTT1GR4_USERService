package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.api.service.AuthService;

@RestController 
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired 
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha) {
        return authService.login(email, senha);
    }
    
}
