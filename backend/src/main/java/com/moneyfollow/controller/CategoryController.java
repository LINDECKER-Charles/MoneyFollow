package com.moneyfollow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneyfollow.model.Category;
import com.moneyfollow.model.User;
import com.moneyfollow.repository.CategoryRepository;
import org.springframework.security.core.Authentication;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    
    private final CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<Category> createCategory(
            @RequestBody Category category,
            @AuthenticationPrincipal User user) {

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        category.setUser(user);
        Category saved = categoryRepository.save(category);
        System.out.println("eeee");
        return ResponseEntity.ok(saved);
    }

}
