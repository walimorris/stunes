package com.morris.stunes.util;

import com.morris.stunes.configuration.Properties;
import org.json.JSONObject;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.Map;

public class AuroraSecretsManagerHelper {

    private static final String AURORA_WRITER_NAME = "aurora-writer-name";
    private static final String AURORA_WRITER_REGION = "aurora-writer-region";
    private static final String AURORA_WRITER_URL = "aurora-writer-url";

    private static final String USER_NAME = "username";
    private static final String PASS_WORD = "password";
    private static final String STUNES_WRITER_URL = "stunes-writer-url";

    private final Map<String, String> auroraProperties;

    public AuroraSecretsManagerHelper() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Properties.class);
        Properties auroraPropertiesContext = context.getBean(Properties.class);
        this.auroraProperties = auroraPropertiesContext.AuroraSecretsProperties();
        context.close();
    }

    public String getAuroraWriterUserName() {
        JSONObject secretString = getAuroraSecretString(auroraProperties.get(AURORA_WRITER_NAME),
                auroraProperties.get(AURORA_WRITER_REGION));
        return secretString.getString(USER_NAME);
    }

    public String getAuroraWriterPassword() {
        JSONObject secretString = getAuroraSecretString(auroraProperties.get(AURORA_WRITER_NAME),
                auroraProperties.get(AURORA_WRITER_REGION));
        return secretString.getString(PASS_WORD);
    }

    public String getAuroraWriterUrl() {
        JSONObject secretString = getAuroraSecretString(auroraProperties.get(AURORA_WRITER_URL),
                auroraProperties.get(AURORA_WRITER_REGION));
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
