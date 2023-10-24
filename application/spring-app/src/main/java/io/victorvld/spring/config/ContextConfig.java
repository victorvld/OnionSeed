package io.victorvld.spring.config;

import io.victorvld.config.AppConfig;
import io.victorvld.domain.usecases.context.Context;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {

    @Bean
    @ConditionalOnProperty(name="environment", havingValue = "test")
    public Context testContext() {
        AppConfig.setupTestUserContext();
        return new Context();
    }

    @Bean
    @ConditionalOnProperty(name="environment", havingValue = "dev", matchIfMissing = true)
    public Context devContext() {
        AppConfig.setupTestUserContext();
        return new Context();
    }
}
