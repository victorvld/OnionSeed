package io.essentials.domain.entities;

public record UserSession(String sessionToken, String expiresAt) {
}
