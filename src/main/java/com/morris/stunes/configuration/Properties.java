package com.morris.stunes.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySources({
        @PropertySource("classpath:secrets.properties"),
        @PropertySource("classpath:application.properties")
})
public class Properties {

    @Value("${secrets.awn}")
    private String secretsAwn;

    @Value("${secrets.awr}")
    private String secretAwr;

    @Value("${secrets.awu}")
    private String secretsAwu;

    @Value("${kinesis.sr}")
    private String kinesisSr;

    @Value("${kinesis.sn}")
    private String kinesisSn;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${spring.jpa.show-sql}")
    private String showSql;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Bean
    public Map<String, String> AuroraSecretsProperties() {
        Map<String, String> auroraSecrets = new HashMap<>();
        auroraSecrets.put("aurora-writer-name", secretsAwn);
        auroraSecrets.put("aurora-writer-region", secretAwr);
        auroraSecrets.put("aurora-writer-url", secretsAwu);
        return auroraSecrets;
    }

    @Bean
    public Map<String, String> KinesisSecretsProperties() {
        Map<String, String> kinesisSecrets = new HashMap<>();
        kinesisSecrets.put("kinesis-stream-region", kinesisSr);
        kinesisSecrets.put("kinesis-stream-name", kinesisSn);
        return kinesisSecrets;
    }

    @Bean
    public Map<String, String> JPADatasourceProperties() {
        Map<String, String> jpaDatasourceProperties = new HashMap<>();
        jpaDatasourceProperties.put("hibernate-dialect", hibernateDialect);
        jpaDatasourceProperties.put("show-sql", showSql);
        jpaDatasourceProperties.put("ddl-auto", ddlAuto);
        return jpaDatasourceProperties;
    }
}
