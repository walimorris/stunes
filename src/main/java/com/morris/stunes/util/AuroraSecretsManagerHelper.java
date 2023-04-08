package com.morris.stunes.util;

import com.morris.stunes.service.Properties;
import org.json.JSONObject;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

public class AuroraSecretsManagerHelper {

    private static final String auroraWriterName = Properties.auroraWriterName;
    private static final String auroraWriterRegion = Properties.auroraWriterRegion;
    private static final String auroraWriterUrl = Properties.auroraWriterUrl;

    private static final String USER_NAME = "username";
    private static final String PASS_WORD = "password";
    private static final String STUNES_WRITER_URL = "stunes-writer-url";

    public static String getAuroraWriterUserName() {
        JSONObject secretString = getAuroraSecretString(auroraWriterName, auroraWriterRegion);
        return secretString.getString(USER_NAME);
    }

    public static String getAuroraWriterPassword() {
        JSONObject secretString = getAuroraSecretString(auroraWriterName, auroraWriterRegion);
        return secretString.getString(PASS_WORD);
    }

    public static String getAuroraWriterUrl() {
        JSONObject secretString = getAuroraSecretString(auroraWriterUrl, auroraWriterRegion);
        return secretString.getString(STUNES_WRITER_URL);
    }

    private static JSONObject getAuroraSecretString(String secretId, String region) {
        SecretsManagerClient secretsManagerClient = ClientHelper.getSecretsManagerClient(region);
        GetSecretValueRequest auroraWriterSecretNameRequest = GetSecretValueRequest.builder()
                .secretId(secretId)
                .build();

        GetSecretValueResponse auroraWriterSecretNameResponse = null;

        try {
            auroraWriterSecretNameResponse = secretsManagerClient.getSecretValue(auroraWriterSecretNameRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        secretsManagerClient.close();
        return new JSONObject(auroraWriterSecretNameResponse.secretString());
    }
}
