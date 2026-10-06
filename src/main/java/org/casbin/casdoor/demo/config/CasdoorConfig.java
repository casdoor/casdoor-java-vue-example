package org.casbin.casdoor.demo.config;

import org.casbin.casdoor.config.Config;
import org.casbin.casdoor.service.AuthService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CasdoorConfig {

    // the settings of casdoor-java-sdk, read from the casdoor.* properties
    @Bean
    @ConfigurationProperties(prefix = "casdoor")
    public Config casdoorSdkConfig() {
        return new Config();
    }

    @Bean
    public AuthService authService(Config config) {
        return new AuthService(config);
    }
}
