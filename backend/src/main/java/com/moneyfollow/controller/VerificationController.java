package com.moneyfollow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moneyfollow.model.User;
import com.moneyfollow.repository.UserRepository;
import com.moneyfollow.repository.VerificationTokenRepository;
import com.moneyfollow.security.VerificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class VerificationController {
    
    private final VerificationTokenRepository tokenRepository;
    private final VerificationService verificationService;
    private final UserRepository userRepository;

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String token) {
        var verificationToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Token invalide"));

        if (verificationToken.isExpired()) {
            tokenRepository.delete(verificationToken);
            return ResponseEntity.badRequest().body("⏳ Le lien de vérification a expiré.");
        }

        User user = verificationToken.getUser();
        user.setVerified(true);
        userRepository.save(user);

        tokenRepository.delete(verificationToken);

        return ResponseEntity.ok("✅ Adresse e-mail vérifiée avec succès !");
    }

    @GetMapping("/send-verify")
    public ResponseEntity<String> sendVerifyMail(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        if (user.isVerified()) {
            return ResponseEntity.badRequest().body("Ton adresse e-mail est déjà vérifiée.");
        }
        tokenRepository.findByUser(user).ifPresent(tokenRepository::delete);
        this.verificationService.sendVerificationEmail(user);
        return ResponseEntity.ok("Mail de vérification envoyé.");
    }
    
}
