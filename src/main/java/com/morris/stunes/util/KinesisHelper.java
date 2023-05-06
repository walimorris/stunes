package com.morris.stunes.util;

import com.morris.stunes.configuration.Properties;
import com.morris.stunes.service.ClickStreamService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @code com.morris.stunes.util.KinesisHelper - is a utility class that reduces redundancy when
 * calling common Amazon Kinesis features and functionality utilized in the stunes application.
 */
@Component("kinesisHelper")
public class KinesisHelper {

    private final Map<String, String> kinesisProperties;
    private final String kinesisRegion;
    private final String kinesisStreamName;

    public KinesisHelper(@Qualifier("kinesisProperties") Map<String, String> kinesisProperties,
                         @Qualifier("kinesisRegion") String kinesisRegion,
                         @Qualifier("kinesisStreamName") String kinesisStreamName) {

        this.kinesisProperties = kinesisProperties;
        this.kinesisRegion = kinesisRegion;
        this.kinesisStreamName = kinesisStreamName;
    }

    /**
     * Get Kinesis Stream Name - works with stunes click stream data.
     *
     * @return {@link String}
     * @see ClickStreamService
     * @see Properties
     */
    public String getKinesisStreamName() {
        return this.kinesisStreamName;
    }

    /**
     * Get AWS service region for the Kinesis Stream utilized in click stream data.
     *
     * @return {@link String}
     * @see ClickStreamService
     * @see Properties
     */
    public String getKinesisRegion() {
        return this.kinesisRegion;
    }
}
