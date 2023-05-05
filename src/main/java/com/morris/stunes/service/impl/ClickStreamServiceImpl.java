package com.morris.stunes.service.impl;

import com.morris.stunes.model.ClickStreamRecord;
import com.morris.stunes.service.ClickStreamService;
import org.springframework.stereotype.Service;

@Service
public class ClickStreamServiceImpl implements ClickStreamService {

    @Override
    public String clickStreamString(ClickStreamRecord record) {
        return record.toString();
    }
}
