package com.moneyfollow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moneyfollow.model.ResetToken;
import com.moneyfollow.model.User;
import com.moneyfollow.repository.ResetTokenRepository;
import com.moneyfollow.repository.UserRepository;
import com.moneyfollow.security.ResetPasswordService;

import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ResetPasswordController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ResetPasswordService resetPasswordService;
    private final ResetTokenRepository tokenRepository;

    @GetMapping("/reset-password")
    public ResponseEntity<String> verifyUser(@RequestParam String token, @RequestParam String newPassword) {
        var verificationToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Token invalide"));

        if (verificationToken.isExpired()) {
            tokenRepository.delete(verificationToken);
            return ResponseEntity.badRequest().body("⏳ Le lien de vérification a expiré.");
        }

        User user = verificationToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(verificationToken);

        return ResponseEntity.ok("✅ Mot de passe réinitialisé avec succès !");
    }

    @GetMapping("/send-reset-password")
    public ResponseEntity<String> sendVerifyMail(@RequestParam String email) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        tokenRepository.findByUser(user).ifPresent(tokenRepository::delete);
        this.resetPasswordService.sendVerificationEmail(user);
        return ResponseEntity.ok("Reset password mail sent.");
    }
}
