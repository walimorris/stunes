package com.morris.stunes.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

/**
 * @code com.morris.stunes.util.ClientHelper - is a utility class that reduces redundancy when
 * calling common AWS service clients in the stunes application.
 */
@Component("clientHelper")
public class ClientHelper {

    @Autowired
    private KinesisHelper kinesisHelper;

    /**
     * Get {@link SecretsManagerClient} for AWS service usage.
     *
     * @param region AWS service region
     * @return {@link SecretsManagerClient}
     */
    public static SecretsManagerClient getSecretsManagerClient(String region) {
        return SecretsManagerClient.builder()
                .region(Region.of(region))
                .build();
    }

    /**
     * Get {@link KinesisClient} for AWS service usage.
     *
     * @return {@link KinesisClient}
     */
    public KinesisClient getKinesisClient() {
        return KinesisClient.builder()
                .region(Region.of(this.kinesisHelper.getKinesisRegion()))
                .build();
    }
}
