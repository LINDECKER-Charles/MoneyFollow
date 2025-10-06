package com.moneyfollow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "name", nullable = false, length = 255)
    @NotBlank(message = "Le nom du produit est obligatoire")
    @Size(max = 255, message = "Le nom ne peut pas dépasser 255 caractères")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "createdAt", nullable = false)
    @NotNull(message = "La date de création est obligatoire")
    private LocalDateTime createdAt;

    @Column(name = "imagePath", columnDefinition = "TEXT")
    private String imagePath;

    @Column(name = "currency", nullable = false, length = 20)
    @NotBlank(message = "La devise est obligatoire")
    @Size(max = 20, message = "La devise ne peut pas dépasser 20 caractères")
    private String currency;

    @Column(name = "price", nullable = false, precision = 15, scale = 2)
    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.00", message = "Le prix doit être positif")
    private BigDecimal price;

    // Relation Many-to-One avec User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull(message = "L'utilisateur est obligatoire")
    private User user;

    // Relation Many-to-Many avec Category via la table TRIER
    @ManyToMany
    @JoinTable(
        name = "TRIER",
        joinColumns = @JoinColumn(name = "id_product"),
        inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private Set<Category> categories;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
