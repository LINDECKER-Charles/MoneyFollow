package com.moneyfollow.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moneyfollow.model.User;
import com.moneyfollow.repository.UserRepository;
import com.moneyfollow.repository.VerificationTokenRepository;
import com.moneyfollow.security.RateLimited;
import com.moneyfollow.security.VerificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class VerificationController {
    
    private final VerificationTokenRepository tokenRepository;
    private final VerificationService verificationService;
    private final UserRepository userRepository;

    @RateLimited(limit = 5, durationSeconds = 200)
    @GetMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyUser(@RequestParam String token) {
        var verificationToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Token invalide"));

        if (verificationToken.isExpired()) {
            tokenRepository.delete(verificationToken);
            return ResponseEntity.badRequest().body(Map.of("message", "⏳ Le lien de vérification a expiré."));
        }

        User user = verificationToken.getUser();
        user.setVerified(true);
        userRepository.save(user);

        tokenRepository.delete(verificationToken);

        return ResponseEntity.ok(Map.of("message", "Adresse e-mail vérifiée avec succès !"));
    }

    @RateLimited(limit = 3, durationSeconds = 1200)
    @PostMapping("/send-verify")
    public ResponseEntity<Map<String, Object>> sendVerifyMail(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        if (user.isVerified()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Ton adresse e-mail est déjà vérifiée."));
        }
        tokenRepository.findByUser(user).ifPresent(tokenRepository::delete);
        this.verificationService.sendVerificationEmail(user);
        return ResponseEntity.ok(Map.of("message", "Mail de vérification envoyé."));
    }
    
}
