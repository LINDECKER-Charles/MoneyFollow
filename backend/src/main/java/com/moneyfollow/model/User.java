package com.moneyfollow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "name", nullable = false, length = 50)
    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 50, message = "Le nom ne peut pas dépasser 50 caractères")
    private String name;

    @Column(name = "email", nullable = false, length = 254)
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    @Size(max = 254, message = "L'email ne peut pas dépasser 254 caractères")
    private String email;

    @Column(name = "password", nullable = false, length = 50)
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(max = 50, message = "Le mot de passe ne peut pas dépasser 50 caractères")
    private String password;

    @Column(name = "role", columnDefinition = "json")
    private String role;

    @Column(name = "createdAt", nullable = false)
    @NotNull(message = "La date de création est obligatoire")
    private LocalDateTime createdAt;

    @Column(name = "isVerified", nullable = false)
    private Boolean isVerified = false;

    // Relations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Category> categories;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
