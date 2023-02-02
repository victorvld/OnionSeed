package io.essentials.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.essentials.adapter.model.WebUser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebUserControllerIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void homePageIT() throws IOException {
        var response = this.template.getForEntity("/", String.class);

        Document doc = Jsoup.parse(response.getBody());
        String title = doc.title();

        Assertions.assertEquals("Login Page", title);
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


    private static Stream<Arguments> loginCasesProvider() {
        return Stream.of(
                Arguments.of("registeredUser@email.io", "password", "Home Page"),
                Arguments.of("registeredUser@email.io", "WrongPassword", "Login Page"),
                Arguments.of("notRegisteredUser@email.io", "password", "Login Page")
        );
    }

    @ParameterizedTest
    @MethodSource("loginCasesProvider")
    public void loginTest(String username, String password, String expectedPage) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", username);
        body.add("password", password);
        var request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = this.template.exchange("/home", HttpMethod.POST, request, String.class);

        var doc = Jsoup.parse(response.getBody());
        var actualPage = doc.title();

        Assertions.assertAll(
                () -> assertEquals(expectedPage, actualPage)
        );
        // read cookie and assert that.
    }

}
