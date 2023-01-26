package io.essentials.domain.entities;

import java.time.LocalDateTime;

public record UserSession(String sessionToken, LocalDateTime expiresAt) {
    public UserSession(String sessionToken, LocalDateTime expiresAt) {
        this.sessionToken = sessionToken;
        this.expiresAt = expiresAt;
    }
}
