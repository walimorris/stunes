package com.morris.stunes.util;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

public class ClientHelper {

    public static SecretsManagerClient getSecretsManagerClient(String region) {
        return SecretsManagerClient.builder()
                .region(Region.of(region))
                .build();
    }
}
