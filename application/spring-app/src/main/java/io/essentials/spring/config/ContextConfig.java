package io.essentials.spring.config;

import io.essentials.config.AppConfig;
import io.essentials.domain.usecases.context.Context;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {

    @Bean
    @ConditionalOnProperty(name="environment", havingValue = "test")
    public Context testContext() {
        System.out.println("Test");
        AppConfig.setupTestUserContext();
        return new Context();
    }

    @Bean
    @ConditionalOnProperty(name="environment", havingValue = "dev", matchIfMissing = true)
    public Context devContext() {
        System.out.println("Dev");
        AppConfig.setupUserContext();
        return new Context();
    }
}
