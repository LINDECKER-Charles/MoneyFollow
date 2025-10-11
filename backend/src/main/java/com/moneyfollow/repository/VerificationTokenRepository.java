package com.moneyfollow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moneyfollow.model.User;
import com.moneyfollow.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByUser(User user);
}