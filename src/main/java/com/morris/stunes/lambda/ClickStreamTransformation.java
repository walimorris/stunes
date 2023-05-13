package com.morris.stunes.lambda;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import java.io.IOException;
import java.util.List;

public class ClickStreamTransformation {

    public String handleRequest(S3Event event, Context context) {
        AmazonS3 client = AmazonS3Client.builder()
                .withRegion(Regions.US_WEST_2)
                .build();

        LambdaLogger logger = context.getLogger();
        List<S3EventNotification.S3EventNotificationRecord> records = event.getRecords();
        for (S3EventNotification.S3EventNotificationRecord record : records) {
            String bucket = record.getS3().getBucket().getName();
            String key = record.getS3().getObject().getKey();
            S3Object object = client.getObject(bucket, key);

            logger.log("record: " + objectContent(object, logger));
        }
        return "success";
    }

    private String objectContent(S3Object object, LambdaLogger logger) {
        String content = null;

        try {
            S3ObjectInputStream inputStream = object.getObjectContent();
            content = IOUtils.toString(inputStream);
        } catch (IOException e) {
            logger.log("Error reading S3Object Content: " + e.getMessage());
            return null;
        }
        return content;
    }
}
