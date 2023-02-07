package io.essentials.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.essentials.adapter.model.WebUser;
import io.essentials.config.AppConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

import java.net.HttpCookie;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSignUpControllerIT {
    @BeforeEach
    void setUp() {
        AppConfig.setupTestUserContext();
    }

    @Autowired
    private TestRestTemplate template;

    @Test
    public void homePageIT() {
        var response = this.template.getForEntity("/", String.class);

        Document doc = Jsoup.parse(response.getBody());
        String title = doc.title();

        Assertions.assertEquals("Login Page", title);
    }


    @Test
    public void signUpPerson() throws JsonProcessingException {
        var headers = new HttpHeaders();
        var email = "u@e.io";
        var password = "password";
        var firstName = "firstName";
        var lastName = "lastName";
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("email", email);
        body.add("password", password);
        body.add("firstName", firstName);
        body.add("lastName", lastName);
        var request = new HttpEntity<>(body, headers);
        var mapper = new ObjectMapper();

        var response = this.template.exchange("/signUp", HttpMethod.POST, request, String.class);

        var res = mapper.readValue(response.getBody(), WebUser.class);

        Assertions.assertAll(
                () -> assertEquals(res.getFirstName(), firstName),
                () -> assertEquals(res.getLastName(), lastName),
                () -> assertEquals(res.getEmail(), email),
                () -> assertNull(res.getPassword())
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

        Assertions.assertEquals(expectedPage, actualPage);

    }

    // TODO: 2/3/23 Fix this tests
    @Disabled("not yet ready , Please ignore.")
    @Test
    public void loginTestCookies() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", "registeredUser@email.io");
        body.add("password", "password");
        var request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = this.template.exchange("/home", HttpMethod.POST, request, String.class);

        // TODO: 2/3/23 remove this ugly thing and do it in an utility class.
        var sessionCookie = response.getHeaders().get("Set-Cookie").get(0);
        var cookie = HttpCookie.parse(sessionCookie).get(0);

        Assertions.assertAll(
                () -> assertEquals(cookie.getName(), "sessionToken"),
                () -> assertFalse(cookie.getValue().isBlank()),
                () -> assertFalse(cookie.getDomain().isBlank()),
                () -> assertEquals(cookie.getPath(), "/home"),
                () -> assertEquals(cookie.getMaxAge(), 86400),
                () -> assertTrue(cookie.getSecure())
        );
    }

}
