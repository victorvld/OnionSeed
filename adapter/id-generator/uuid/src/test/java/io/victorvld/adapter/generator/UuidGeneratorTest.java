package io.victorvld.adapter.generator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UuidGeneratorTest {

    @Test
    void uuidGeneratorTest() {
        var uuid = new UuidGenerator().generate();

        assertAll(
                () -> assertEquals(36, uuid.length()),
                () -> assertEquals(Arrays.stream(uuid.split("(?<!^)(?=-)")).count(), 5)
        );
    }
}