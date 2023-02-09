package io.essentials.spring.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.essentials.adapter.controller.login.LoginController;
import io.essentials.adapter.controller.signup.SignUpController;
import io.essentials.adapter.presenter.login.LoginPresenter;
import io.essentials.usecases.signup.interactor.SignUpInteractor;
import io.essentials.usecases.login.interactor.LoginInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Each Controller represents a bean this way sprint have control over our class instances.
 *  An alternative approach would be to use a factory pattern.
 *  However, each request would create controller, interactor and presenter every time is handled.
 *  Unless a singleton Pattern will be also implemented. </p>
 *
 *  <p>Decision made: Delegate to spring the instantiation of our controller, presenters, and interactor.</p>
 */
@Configuration
public class Config {

    @Bean
    public SignUpController signUpController() {
        return new SignUpController(new SignUpInteractor());
    }

    @Bean
    public LoginController loginController() {
        return new LoginController(new LoginInteractor(new LoginPresenter()));
    }

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
}
