package com.moneyfollow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Builder.Default
    @Column(nullable = false)
    private int qtt = 1;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(nullable = false)
    private String imagePath = "default_product.png";

    @Builder.Default
    @Column(nullable = false, length = 20)
    private String currency = "EUR";

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    // Relations
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
        name = "trier",
        joinColumns = @JoinColumn(name = "id_product"),
        inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<Category> categories;

    // Getters & setters
}
