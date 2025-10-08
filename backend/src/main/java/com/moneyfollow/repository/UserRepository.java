package com.moneyfollow.repository;

import com.moneyfollow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.StackWalker.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    boolean existsByEmail(String email);
}