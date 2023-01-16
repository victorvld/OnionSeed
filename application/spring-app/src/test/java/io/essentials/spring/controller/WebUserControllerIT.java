package io.essentials.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.essentials.adapter.model.WebUser;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebUserControllerIT {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void homePageIT() throws IOException {
        var classLoader = getClass().getClassLoader();
        var file = new File(Objects.requireNonNull(classLoader.getResource("templates/index.html")).getFile());
        var homePage = FileUtils.readFileToString(file, "UTF-8");

        var response = this.template.getForEntity("/", String.class);

        Assertions.assertEquals(homePage, response.getBody());
    }

    @Test
    public void createUserIT() throws JsonProcessingException {
        var webUser = new WebUser("u@email.com", "password", "user", "user");
        var mapper = new ObjectMapper();
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var request = new HttpEntity<>(mapper.writeValueAsString(webUser), headers);

        ResponseEntity<String> response = this.template.exchange("/users", HttpMethod.POST, request, String.class);

        var result = mapper.readValue(response.getBody(), WebUser.class);

        Assertions.assertAll(
                () -> assertEquals(result.getFirstName(), webUser.getFirstName()),
                () -> assertEquals(result.getLastName(), webUser.getLastName()),
                () -> assertEquals(result.getEmail(), webUser.getEmail()),
                () -> assertNull(result.getPassword())
        );
    }
}
