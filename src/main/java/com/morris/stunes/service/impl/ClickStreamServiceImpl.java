package com.morris.stunes.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.morris.stunes.model.ClickStreamRecord;
import com.morris.stunes.service.ClickStreamService;
import com.morris.stunes.util.ClientHelper;
import com.morris.stunes.util.KinesisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.KinesisException;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;
import software.amazon.awssdk.services.kinesis.model.PutRecordResponse;

import java.nio.ByteBuffer;

@Service
public class ClickStreamServiceImpl implements ClickStreamService {

    @Autowired
    KinesisHelper kinesisHelper;

    @Autowired
    ClientHelper clientHelper;

    @Override
    public String writeClickStream(ClickStreamRecord record) {
        byte[] bytes = getClickStreamRecordAsJsonBytes(record);
        if (bytes != null) {
            PutRecordResponse response = writeClickStreamRecordToKinesis(bytes, record);
            return response != null ? response.sequenceNumber() : null;
        }
        return null;
    }

    /**
     * Get Click Stream Record as JSON bytes. Uses {@link ObjectMapper} to marshall
     * {@link ClickStreamRecord} to JSON and returns this value as bytes.
     *
     * @param record {@link ClickStreamRecord}
     * @return byte[]
     */
    private byte[] getClickStreamRecordAsJsonBytes(ClickStreamRecord record) {
        ObjectMapper mapper = new ObjectMapper();
        byte[] bytes = new byte[0];

        try {
            String clickStreamRecordJson = mapper.writeValueAsString(record);
            if (clickStreamRecordJson != null) {
                bytes = mapper.writeValueAsBytes(clickStreamRecordJson);
            }
        } catch (JsonProcessingException e) {
            System.out.println("Can not marshall json object to json string: " + e.getMessage());
        }
        return bytes;
    }

    /**
     * Writes given click stream record, in the form of byte[], to a Kinesis Stream.
     * Builds a {@link PutRecordRequest} using stream name, a partition key, and the
     * record bytes. Records are partitioned by userId from the click event record
     * the unique value.
     *
     * @param recordBytes click stream records in bytes
     * @param record {@link ClickStreamRecord}
     *
     * @return {@link PutRecordResponse}
     * @see ClientHelper
     * @see KinesisHelper
     */
    private PutRecordResponse writeClickStreamRecordToKinesis(byte[] recordBytes, ClickStreamRecord record) {
        PutRecordResponse putRecordResponse;
        KinesisClient kinesisClient = clientHelper.getKinesisClient();
        PutRecordRequest putRecordRequest = PutRecordRequest.builder()
                .streamName(kinesisHelper.getKinesisStreamName())
                .partitionKey(record.getUserId())
                .data(SdkBytes.fromByteBuffer(ByteBuffer.wrap(recordBytes)))
                .build();
        try {
            putRecordResponse = kinesisClient.putRecord(putRecordRequest);
        } catch (KinesisException e) {
            System.out.println("Error putting record to kinesis stream: " + e.getMessage());
            kinesisClient.close();
            return null;
        }
        kinesisClient.close();
        return putRecordResponse;
    }
}
