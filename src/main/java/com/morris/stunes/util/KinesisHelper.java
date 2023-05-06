package com.morris.stunes.util;

import com.morris.stunes.configuration.Properties;
import com.morris.stunes.service.ClickStreamService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.Map;

/**
 * @code com.morris.stunes.util.KinesisHelper - is a utility class that reduces redundancy when
 * calling common Amazon Kinesis features and functionality utilized in the stunes application.
 */
public class KinesisHelper {

    private static final String KINESIS_STREAM_NAME = "kinesis-stream-name";
    private static final String KINESIS_REGION = "kinesis-stream-region";

    private final Map<String, String> kinesisProperties;

    public KinesisHelper() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Properties.class);
        Properties kinesisPropertiesContext = context.getBean(Properties.class);
        this.kinesisProperties = kinesisPropertiesContext.KinesisSecretsProperties();
        context.close();
    }

    /**
     * Get Kinesis Stream Name - works with stunes click stream data.
     *
     * @return {@link String}
     * @see ClickStreamService
     * @see Properties
     */
    public String getKinesisStreamName() {
        return this.kinesisProperties.get(KINESIS_STREAM_NAME);
    }

    /**
     * Get AWS service region for the Kinesis Stream utilized in click stream data.
     *
     * @return {@link String}
     * @see ClickStreamService
     * @see Properties
     */
    public String getKinesisRegion() {
        return this.kinesisProperties.get(KINESIS_REGION);
    }
}
