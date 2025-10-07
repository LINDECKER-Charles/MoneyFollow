package com.moneyfollow.repository;

import com.moneyfollow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.StackWalker.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
}