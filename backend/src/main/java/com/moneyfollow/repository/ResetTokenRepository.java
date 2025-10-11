package com.moneyfollow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moneyfollow.model.ResetToken;
import com.moneyfollow.model.User;

public interface ResetTokenRepository extends JpaRepository<ResetToken, Long> {
    Optional<ResetToken> findByToken(String token);
    Optional<ResetToken> findByUser(User user);
}
