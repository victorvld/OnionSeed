package io.essentials.domain.entities;

import java.time.LocalDateTime;

public record UserSession(String sessionToken, LocalDateTime expiresAt) {
}
