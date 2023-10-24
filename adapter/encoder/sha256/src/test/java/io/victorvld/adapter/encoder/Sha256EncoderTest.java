package io.victorvld.adapter.encoder;

import io.victorvld.domain.usecases.encoder.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Sha256EncoderTest {

    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        encoder = new Sha256Encoder();
    }

    private static Stream<Arguments> provideStringsForEncoding() {
        return Stream.of(
                Arguments.of("test", "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"),
                Arguments.of("!Qw$eR*(L@2*bVm/><6", "f30bf76c91128d20adf43d62440728f5c1b89c8b6af8c37516853fa93bf959fa")
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForEncoding")
    void userValidationExceptionsTest(String password, String encodedPassword) {
        var result = encoder.encode(password);

        assertEquals(encodedPassword, result);
    }
}