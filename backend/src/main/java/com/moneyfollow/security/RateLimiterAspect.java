package com.moneyfollow.security;

import io.github.bucket4j.*;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.*;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimiterAspect {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Around("@annotation(com.moneyfollow.security.RateLimited)")
    public Object enforceRateLimit(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();

        String ip = request.getRemoteAddr();

        // Récupère les infos de l’annotation
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        RateLimited rateLimited = signature.getMethod().getAnnotation(RateLimited.class);

        int limit = rateLimited.limit();
        long duration = rateLimited.durationSeconds();

        // clé = IP + nom de la méthode (unique par route)
        String key = ip + ":" + signature.getMethod().getName();

        Bucket bucket = buckets.computeIfAbsent(key,
                k -> createBucket(limit, Duration.ofSeconds(duration)));

        if (bucket.tryConsume(1)) {
            return pjp.proceed(); // exécute la méthode du controller
        } else {
            throw new RateLimitExceededException("Trop de requêtes, réessaie plus tard.");
        }
    }

    private Bucket createBucket(int capacity, Duration duration) {
        Bandwidth limit = Bandwidth.builder()
                .capacity(capacity)
                .refillIntervally(capacity, duration)
                .build();

        return Bucket.builder().addLimit(limit).build();
    }
}