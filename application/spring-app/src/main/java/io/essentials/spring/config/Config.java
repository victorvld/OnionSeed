package io.essentials.spring.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.essentials.adapter.controller.UserController;
import io.essentials.config.AppConfig;
import io.essentials.domain.usecases.UserInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    @Bean
    public UserController userController() {
        AppConfig.setupUserContext();
        return new UserController(new UserInteractor());
    }
}
