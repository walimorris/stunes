package com.morris.stunes.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.amazonaws.services.lambda.runtime.events.KinesisFirehoseEvent;
import com.amazonaws.services.lambda.runtime.events.KinesisFirehoseEvent.Record;

import java.util.List;

public class ClickStreamTransformation {

    public KinesisEvent.KinesisEventRecord handleRequest(KinesisFirehoseEvent event, Context context) {
        LambdaLogger logger = context.getLogger();
        List<Record> records = event.getRecords();
        for (KinesisFirehoseEvent.Record record : records) {
            logger.log(record.getRecordId() + " Data: " + record.getData().toString());
        }
        return null;
    }
}
