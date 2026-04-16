package dev.cinefy.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String username, String email) {
}
