package com.morris.stunes.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:secrets.properties")
public class Properties {

    @Autowired
    Environment environment;

    @Bean
    public Map<String, String> auroraSecretsProperties() {
        Map<String, String> auroraSecrets = new HashMap<>();
        auroraSecrets.put("aurora-writer-name", environment.getProperty("secrets.awn"));
        auroraSecrets.put("aurora-writer-region", environment.getProperty("secrets.awr"));
        auroraSecrets.put("aurora-writer-url", environment.getProperty("secrets.awu"));
        return auroraSecrets;
    }
}
