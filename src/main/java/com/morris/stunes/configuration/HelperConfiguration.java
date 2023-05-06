package com.morris.stunes.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class HelperConfiguration {

    private static final String KINESIS_STREAM_NAME = "kinesis-stream-name";
    private static final String KINESIS_REGION = "kinesis-stream-region";

    @Bean
    @Qualifier("kinesisProperties")
    public Map<String, String> KinesisProperties() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Properties.class);
        Properties kinesisPropertiesContext = context.getBean(Properties.class);
        context.close();
        return kinesisPropertiesContext.KinesisSecretsProperties();
    }

    @Bean
    @Qualifier("kinesisRegion")
    public String KinesisRegion() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Properties.class);
        Properties kinesisPropertiesContext = context.getBean(Properties.class);
        context.close();
        return kinesisPropertiesContext.KinesisSecretsProperties().get(KINESIS_REGION);
    }

    @Bean
    @Qualifier("kinesisStreamName")
    public String KinesisStreamName() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Properties.class);
        Properties kinesisPropertiesContext = context.getBean(Properties.class);
        context.close();
        return kinesisPropertiesContext.KinesisSecretsProperties().get(KINESIS_STREAM_NAME);
    }
}
