package org.casbin.casdoor.demo.config;

import org.casbin.casdoor.service.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CasdoorConfiguration.class)
public class CasdoorAutoConfigure {
    @Bean
    @ConditionalOnMissingBean
    CasdoorAuthService getAuthService(CasdoorConfiguration config) {
        return new CasdoorAuthService(config);
    }

    @Bean
    @ConditionalOnMissingBean
    CasdoorUserService getUserService(CasdoorConfiguration config){
        return new CasdoorUserService(config);
    }

    @Bean
    @ConditionalOnMissingBean
    CasdoorEmailService getEmailService(CasdoorConfiguration config){
        return new CasdoorEmailService(config);
    }

    @Bean
    @ConditionalOnMissingBean
    CasdoorSmsService getSmsService(CasdoorConfiguration config){
        return new CasdoorSmsService(config);
    }

    @Bean
    @ConditionalOnMissingBean
    CasdoorResourceService getResourceService(CasdoorConfiguration config){
        return new CasdoorResourceService(config);
    }



}
