package com.moneyfollow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.moneyfollow.dto.UserDTORecord;
import com.moneyfollow.model.User;
import com.moneyfollow.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @GetMapping
    public ResponseEntity<UserDTORecord> getUser(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        UserDTORecord userDTO = new UserDTORecord(
            user.getName(),
            user.getEmail(),
            user.isVerified(),
            user.getCreatedAt()
        );
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping
    public ResponseEntity<UserDTORecord> patchAuthenticatedUser(
        @AuthenticationPrincipal User user,
        @RequestBody Map<String, Object> updates
    ) {
        // Vérifie que l'utilisateur est bien connecté
        if (user == null) {
            return ResponseEntity.status(401).build();
        } 

        // Appliquer uniquement les champs présents dans la requête
        if (updates.containsKey("name")) {
            user.setName((String) updates.get("name"));
        }
        if (updates.containsKey("email")) {
            user.setEmail((String) updates.get("email"));
            user.setVerified(false);
        }

        if (updates.containsKey("password")) {
            user.setPassword(passwordEncoder.encode((String) updates.get("password")));
        }

        User updatedUser = userRepository.save(user);

        UserDTORecord userDTO = new UserDTORecord(
            updatedUser.getName(),
            updatedUser.getEmail(),
            updatedUser.isVerified(),
            updatedUser.getCreatedAt()
        );
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/search/email")
    public ResponseEntity<Boolean> emailIsTaken(
        @AuthenticationPrincipal User user,
        @RequestParam String value) {
        
        if (user == null) {
            return ResponseEntity.status(401).build();
        } 

        boolean exists = userRepository.existsByEmail(value);

        return ResponseEntity.ok(exists);
    }

    



}
