package com.morris.stunes.configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration to create a {@link Bean} for Hibernate Module for Jackson. SpringBoot's
 * {@link org.springframework.http.converter.json.Jackson2ObjectMapperBuilder} will use
 * this via autoconfiguration, and all ObjectMapper instance will use the Spring Boot
 * defaults plus customizations.
 */
@Configuration
public class JsonConfiguration {

    @Bean
    public Module hibernateModule() {
        return new Hibernate5Module();
    }
}
