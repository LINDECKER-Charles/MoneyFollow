package com.moneyfollow.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moneyfollow.model.User;
import com.moneyfollow.repository.ResetTokenRepository;
import com.moneyfollow.repository.UserRepository;
import com.moneyfollow.security.RateLimited;
import com.moneyfollow.security.ResetPasswordService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ResetPasswordController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ResetPasswordService resetPasswordService;
    private final ResetTokenRepository tokenRepository;

    @RateLimited(limit = 5, durationSeconds = 300)
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, Object>> verifyUser(@RequestParam String token, @RequestParam String newPassword) {
        var verificationToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Token invalide"));

        if (verificationToken.isExpired()) {
            tokenRepository.delete(verificationToken);
            return ResponseEntity.badRequest().body(Map.of("message", "⏳ Le lien de vérification a expiré."));
        }

        User user = verificationToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(verificationToken);

        return ResponseEntity.ok(Map.of("message", "Mot de passe réinitialisé avec succès !"));
    }

    @RateLimited(limit = 2, durationSeconds = 600)
    @PostMapping("/send-reset-password")
    public ResponseEntity<Map<String, Object>> sendVerifyMail(@RequestParam String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        tokenRepository.findByUser(user).ifPresent(tokenRepository::delete);
        this.resetPasswordService.sendVerificationEmail(user);
        return ResponseEntity.ok(Map.of("message", "Reset password mail sent."));
    }
}
