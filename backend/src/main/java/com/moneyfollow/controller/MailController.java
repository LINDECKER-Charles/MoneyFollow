package com.moneyfollow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
;

@RestController
@RequestMapping("/mail-test")
public class MailController {
    
    private final JavaMailSender mailSender;

    public MailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping
    public String sendTestMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("charles.lindecker@outlook.fr");
        message.setSubject("🩸 Test du serveur Bloodsouls Mail");
        message.setText("Ceci est un test d'envoi depuis le serveur SMTP Bloodsouls.");
        message.setFrom("noreply@bloodsouls-mail.com");

        mailSender.send(message);
        return "Mail envoyé avec succès !";
    }
}
