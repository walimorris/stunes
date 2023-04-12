package com.morris.stunes.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySources({
        @PropertySource("classpath:secrets.properties"),
        @PropertySource("classpath:application.properties")
})
public class Properties {

    @Autowired
    Environment environment;

    @Bean
    public Map<String, String> AuroraSecretsProperties() {
        Map<String, String> auroraSecrets = new HashMap<>();
        auroraSecrets.put("aurora-writer-name", environment.getProperty("secrets.awn"));
        auroraSecrets.put("aurora-writer-region", environment.getProperty("secrets.awr"));
        auroraSecrets.put("aurora-writer-url", environment.getProperty("secrets.awu"));
        return auroraSecrets;
    }

    @Bean
    public Map<String, String> JPADatasourceProperties() {
        Map<String, String> jpaDatasourceProperties = new HashMap<>();
        jpaDatasourceProperties.put("hibernate-dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        jpaDatasourceProperties.put("show-sql", environment.getProperty("spring.jpa.show-sql"));
        jpaDatasourceProperties.put("ddl-auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        return jpaDatasourceProperties;
    }
}
