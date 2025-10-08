package com.moneyfollow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.moneyfollow.model.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 254, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Builder.Default
    @Column(nullable = false)
    private boolean isVerified = false;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Relations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference("user-products")
    @JsonIgnore
    private List<Product> products;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference("user-categories")
    @JsonIgnore
    private List<Category> categories;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Getters & setters

}
