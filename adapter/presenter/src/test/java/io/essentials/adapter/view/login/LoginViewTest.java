package io.essentials.adapter.view.login;

import io.essentials.adapter.presenter.response.ViewResponse;
import io.essentials.adapter.utilities.ViewTemplate;
import io.essentials.domain.usecases.view.View;
import io.essentials.usecases.login.response.LoginResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginViewTest {
    private static ViewTemplate internalServerErrorPage;
    private static ViewTemplate loginPage;
    private static ViewTemplate homeUserPage;
    private View view;

    @BeforeAll
    static void setup() throws IOException {
        homeUserPage = ViewTemplate.create("templates/home_user_page.html");
        loginPage = ViewTemplate.create("templates/login_page.html");
        internalServerErrorPage = ViewTemplate.create("templates/500_error_page.html");
    }

    @BeforeEach
    void init() {
        view = new LoginView();
    }

    private static Stream<Arguments> invalidArgumentsProvider() {
        return Stream.of(
                // Malformed Login Response -> Redirect to HTTP 500
                Arguments.of(null, 500, null, internalServerErrorPage.getContent()),
                Arguments.of(new LoginResponse(null, null), 500, null, internalServerErrorPage.getContent()),
                Arguments.of(new LoginResponse(Collections.emptyMap(), null), 500, null, internalServerErrorPage.getContent()),
                Arguments.of(new LoginResponse(Collections.emptyMap(), Collections.emptyMap()), 500, null, internalServerErrorPage.getContent()),
                Arguments.of(new LoginResponse(Map.of("k", "v"), Collections.emptyMap()), 500, null, internalServerErrorPage.getContent()),
                // Successful Response -> Redirect to User Home Page
                Arguments.of(new LoginResponse(Map.of("sessionToken", "12345678"), Collections.emptyMap()), 200, null, homeUserPage.getContent())
                // Login Error Response -> Stay on Login Page and state the particular login error that occurs.
                // TODO: 2/10/23 Continue with the Login errors 
/*                Arguments.of(new LoginResponse(Collections.emptyMap(),
                                Map.of("incorrectPasswordOrNonExistingAccount", "Your password is incorrect or this account doesn’t exist.")),
                        401, null, loginPage.getContent())*/
        );
    }

    @ParameterizedTest
    @MethodSource("invalidArgumentsProvider")
    void WhenGettingLoginResponseThenGenerateViewTest(LoginResponse loginResponse,
                                                 int expectedStatusCode,
                                                 Map<String, List<String>> expectedHeaders,
                                                 String expectedContent) {

        var viewResponse = (ViewResponse) view.generateView(loginResponse);

        assertAll(
                () -> assertEquals(expectedStatusCode, viewResponse.statusCode()),
                () -> assertEquals(expectedHeaders, viewResponse.headers()),
                () -> assertEquals(expectedContent, viewResponse.content())
        );
    }

}
