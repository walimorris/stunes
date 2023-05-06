package com.morris.stunes.util;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

/**
 * @code com.morris.stunes.util.ClientHelper - is a utility class that reduces redundancy when
 * calling common AWS service clients in the stunes application.
 */
public class ClientHelper {

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
     * @param region AWS service region
     * @return {@link KinesisClient}
     */
    public static KinesisClient getKinesisClient(String region) {
        return KinesisClient.builder()
                .region(Region.of(region))
                .build();
    }
}
