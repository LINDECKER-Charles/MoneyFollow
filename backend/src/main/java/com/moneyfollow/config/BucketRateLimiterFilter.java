package com.moneyfollow.config;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.time.Duration;

import org.springframework.web.filter.OncePerRequestFilter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BucketRateLimiterFilter extends OncePerRequestFilter {

    // Un cache global des seaux (1 IP = 1 bucket par endpoint)
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String ip = request.getRemoteAddr();
        String path = request.getRequestURI();

        Bucket bucket = getBucketFor(ip, path);

        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(429);
            response.getWriter().write("⏳ Trop de requêtes sur ce point d'accès. Réessaie plus tard.");
        }
    }

    private Bucket getBucketFor(String ip, String path) {
        String key = ip + ":" + path;

        // Si on a déjà un bucket, on le réutilise
        return cache.computeIfAbsent(key, k -> {
            // Configuration par endpoint
            if (path.equals("/auth/login")) {
                return createBucket(3, Duration.ofMinutes(5)); // 3 requêtes / 5 min
            } else {
                return createBucket(100, Duration.ofMinutes(1)); // par défaut : 100 / min
            }
        });
    }

    private Bucket createBucket(int capacity, Duration period) {
        Bandwidth limit = Bandwidth.builder()
                .capacity(capacity)
                .refillIntervally(capacity, period)
                .build();

        return Bucket.builder()
                .addLimit(limit)
                .build();
    }
    
}
