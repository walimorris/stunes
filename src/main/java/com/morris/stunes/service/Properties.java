package com.morris.stunes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {

    public static String auroraWriterName;
    public static String auroraWriterRegion;
    public static String auroraWriterUrl;

    @Autowired
    public Properties(@Value("${secrets.aurora-writer.name}") String auroraWriterName,
                      @Value("${secrets.aurora-writer.region}") String auroraWriterRegion,
                      @Value("${secrets.aurora-writer.url}") String auroraWriterUrl) {

        Properties.auroraWriterName = auroraWriterName;
        Properties.auroraWriterRegion = auroraWriterRegion;
        Properties.auroraWriterUrl = auroraWriterUrl;
    }
}
