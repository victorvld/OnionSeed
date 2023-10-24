package io.victorvld.spring.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CookiesUtilsTest {

    private static Stream<Arguments> invalidArgumentsProvider() {
        return Stream.of(
                Arguments.of(null, "value"),
                Arguments.of(" ", "value"),
                Arguments.of("name", null),
                Arguments.of("name", " ")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidArgumentsProvider")
    void InvalidArgumentsTest(String name, String value) {
        var actualCookie = CookiesUtils.createCookie(name, value, "domain");

        assertFalse(actualCookie.isPresent());
    }

    private static Stream<Arguments> validArgumentsProvider() {
        return Stream.of(

                Arguments.of(null, false),
                Arguments.of("domain", true)
        );
    }

    @ParameterizedTest
    @MethodSource("validArgumentsProvider")
    void nullDomainCookie(String domain, boolean domainParamIncluded) {

        var actualCookie = CookiesUtils.createCookie("name", "value", domain);

        assertAll(
                () -> assertTrue(actualCookie.isPresent()),
                () -> assertEquals(domainParamIncluded, actualCookie.get().getAttributes().containsKey("domain")),
                () -> assertEquals("/home", actualCookie.get().getPath()),
                () -> assertEquals(86400, actualCookie.get().getMaxAge()),
                () -> assertTrue(actualCookie.get().getSecure()),
                () -> assertTrue(actualCookie.get().isHttpOnly())

        );

    }
}
