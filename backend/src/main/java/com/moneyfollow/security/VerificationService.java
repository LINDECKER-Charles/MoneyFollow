package com.moneyfollow.security;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moneyfollow.model.User;
import com.moneyfollow.model.VerificationToken;
import com.moneyfollow.repository.VerificationTokenRepository;
import com.moneyfollow.service.MailService;

@Service
public class VerificationService {
    
    private final VerificationTokenRepository tokenRepository;
    private final MailService emailService;

    @Value("${app.frontend.url}")
    private String frontendUrl;

    public VerificationService(VerificationTokenRepository tokenRepository, MailService emailService) {
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }

    public void sendVerificationEmail(User user) {
        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(15));

        tokenRepository.save(verificationToken);

        String link = frontendUrl + "/verify?token=" + token;
        emailService.sendSimpleMail(
            user.getEmail(),
            "Vérifie ton adresse e-mail MoneyFollow",
            "Bienvenue dans le Rêve du Chasseur.\n\n" +
            "Clique sur ce lien pour vérifier ton adresse :\n" + link + "\n\n" +
            "Ce lien expire dans 30 minutes."
        );
    }

}
