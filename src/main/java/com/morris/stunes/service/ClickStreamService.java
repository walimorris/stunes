package com.morris.stunes.service;

import com.morris.stunes.model.ClickStreamRecord;

public interface ClickStreamService {

    /**
     * Writes click event data from application layer to a Kinesis data stream
     * for data stream processing.
     *
     * @param record {@link ClickStreamRecord} serialized from click event properties
     * @return {@link String} Kinesis PutRecord Response Sequence Number
     */
    String writeClickStream(ClickStreamRecord record);
}
