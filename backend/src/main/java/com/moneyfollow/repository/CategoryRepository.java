package com.moneyfollow.repository;

import com.moneyfollow.model.Category;
import com.moneyfollow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);
    List<Category> findByUserAndNameContainingIgnoreCase(User user, String name);
}