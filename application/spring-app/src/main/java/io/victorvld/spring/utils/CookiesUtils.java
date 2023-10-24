package io.victorvld.spring.utils;

import jakarta.servlet.http.Cookie;

import java.util.Optional;

public class CookiesUtils {
    public static Optional<Cookie> createCookie(String name, String value, String domain) {
        if (name == null || name.isBlank() || value == null || value.isBlank()) {
            return Optional.empty();
        } else {
            var sessionToken = new Cookie(name, value);
            sessionToken.setDomain(domain);
            // TODO: 2/6/23 This could be extended in the future.
            sessionToken.setPath("/home");
            // TODO: 2/3/23 This should be a configurable param and be align with token expiration.
            sessionToken.setMaxAge(60 * 60 * 24);
            sessionToken.setSecure(true);
            sessionToken.setHttpOnly(true);
            return Optional.of(sessionToken);
        }
    }
}
