package io.essentials.spring.utils;

import jakarta.servlet.http.Cookie;

public class CookiesUtils {
    public static Cookie createCookie(String name, String value, String domain) {
        var sessionToken = new Cookie(name, value);
        // TODO: 2/3/23 Find out how to get the domain from the request
        sessionToken.setDomain(domain);
        sessionToken.setPath("/home");
        // TODO: 2/3/23 This should be a configurable param and be align with token expiration.
        sessionToken.setMaxAge(60 * 60 * 24);
        sessionToken.setSecure(true);
        sessionToken.setHttpOnly(true);
        return sessionToken;
    }
}
