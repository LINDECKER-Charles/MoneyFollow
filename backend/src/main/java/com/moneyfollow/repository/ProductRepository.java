package com.moneyfollow.repository;

import com.moneyfollow.model.Product;
import com.moneyfollow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUser(User user);
    List<Product> findByUserAndNameContainingIgnoreCase(User user, String name);
}