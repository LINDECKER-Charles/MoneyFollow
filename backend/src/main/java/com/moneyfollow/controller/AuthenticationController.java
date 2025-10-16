package com.moneyfollow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneyfollow.auth.AuthenticationRequest;
import com.moneyfollow.auth.AuthenticationResponse;
import com.moneyfollow.auth.RegisterRequest;
import com.moneyfollow.security.AuthentificationService;
import com.moneyfollow.security.RateLimited;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final AuthentificationService service;

    @PostMapping("/register")
    @RateLimited(limit = 5, durationSeconds = 300)
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    @RateLimited(limit = 6, durationSeconds = 300)
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}
