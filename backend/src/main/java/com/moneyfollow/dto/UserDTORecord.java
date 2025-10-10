package com.moneyfollow.dto;

import java.time.LocalDateTime;

public record UserDTORecord(String name, String email, Boolean isVerified, LocalDateTime createdAt) {}

