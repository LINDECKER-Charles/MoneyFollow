package com.moneyfollow.security;

public class RateLimitExceededException extends RuntimeException {

    private final String ip;
    private final long retryAfterSeconds;

    public RateLimitExceededException(String message) {
        super(message);
        this.ip = null;
        this.retryAfterSeconds = 0;
    }

    public RateLimitExceededException(String message, String ip, long retryAfterSeconds) {
        super(message);
        this.ip = ip;
        this.retryAfterSeconds = retryAfterSeconds;
    }

    public String getIp() {
        return ip;
    }

    public long getRetryAfterSeconds() {
        return retryAfterSeconds;
    }
}
