package com.moneyfollow.security;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.moneyfollow.model.ResetToken;
import com.moneyfollow.model.User;
import com.moneyfollow.repository.ResetTokenRepository;
import com.moneyfollow.service.MailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {
    
    private final ResetTokenRepository tokenRepository;

    private final MailService emailService;

    @Value("${app.frontend.url}")
    private String frontendUrl;

    @Async
    public void sendVerificationEmail(User user) {
        String token = UUID.randomUUID().toString();

        ResetToken verificationToken = new ResetToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(15));

        tokenRepository.save(verificationToken);

        String link = frontendUrl + "/reset?token=" + token;
        emailService.sendSimpleMail(
            user.getEmail(),
            "Réinitialisation de ton mot de passe MoneyFollow",
                    "Bonjour,\n\n" +
                    "Tu as demandé à réinitialiser ton mot de passe MoneyFollow.\n" +
                    "Si tu es à l'origine de cette demande, clique sur le lien ci-dessous :\n\n" +
                    link + "\n\n" +
                    "Ce lien expirera dans 15 minutes pour des raisons de sécurité.\n\n" +
                    "Si tu n’as pas demandé cette réinitialisation, ignore simplement ce message.\n\n" +
                    "L’équipe MoneyFollow."
        );
    }
}
