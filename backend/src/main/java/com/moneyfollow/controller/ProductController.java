package com.moneyfollow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.moneyfollow.model.Product;
import com.moneyfollow.model.User;
import com.moneyfollow.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST permettant de gérer les produits d'un utilisateur authentifié.
 * <p>
 * Ce contrôleur expose des routes CRUD (Create, Read, Update, Delete) sécurisées
 * pour la ressource {@link Product}. Chaque opération vérifie que l'utilisateur
 * connecté est bien propriétaire du produit concerné avant d'autoriser l'accès.
 * </p>
 *
 * <p>Routes exposées :</p>
 * <ul>
 *   <li><b>POST /api/products</b> — Crée un nouveau produit pour l'utilisateur connecté.</li>
 *   <li><b>GET /api/products</b> — Récupère la liste des produits appartenant à l'utilisateur connecté.</li>
 *   <li><b>GET /api/products/{id}</b> — Récupère un produit précis s'il appartient à l'utilisateur connecté.</li>
 *   <li><b>GET /api/products/search?name=...</b> — Recherche les produits de l'utilisateur dont le nom contient la chaîne donnée.</li>
 *   <li><b>PUT /api/products/{id}</b> — Met à jour un produit existant appartenant à l'utilisateur connecté.</li>
 *   <li><b>DELETE /api/products/{id}</b> — Supprime un produit appartenant à l'utilisateur connecté.</li>
 * </ul>
 *
 * <p>Chaque requête nécessite un token JWT valide pour accéder aux routes protégées.</p>
 *
 * @author Charles
 * @version 1.0
 * @see Product
 * @see com.moneyfollow.repository.ProductRepository
 */


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductRepository productRepository;


    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody Product product,
            @AuthenticationPrincipal User user) {

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        product.setUser(user);
        Product saved = productRepository.save(product);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @AuthenticationPrincipal User user) {

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        List<Product> products = productRepository.findByUser(user);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        Optional<Product> product = productRepository.findById(id);
        
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Vérifier que le produit appartient à l'utilisateur
        if (!product.get().getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(product.get());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam String name,
            @AuthenticationPrincipal User user) {

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        List<Product> products = productRepository.findByUserAndNameContainingIgnoreCase(user, name);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product productDetails,
            @AuthenticationPrincipal User user) {

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        Optional<Product> productOptional = productRepository.findById(id);
        
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product product = productOptional.get();

        // Vérifier que le produit appartient à l'utilisateur
        if (!product.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }

        // Mettre à jour les champs
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setCurrency(productDetails.getCurrency());
        product.setImagePath(productDetails.getImagePath());
        product.setCategories(productDetails.getCategories());
        product.setQtt(productDetails.getQtt());

        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        Optional<Product> productOptional = productRepository.findById(id);
        
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product product = productOptional.get();

        // Vérifier que le produit appartient à l'utilisateur
        if (!product.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }

        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
