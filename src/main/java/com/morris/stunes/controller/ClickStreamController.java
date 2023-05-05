package com.morris.stunes.controller;

import com.morris.stunes.model.ClickStreamRecord;
import com.morris.stunes.service.impl.ClickStreamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClickStreamController {

    @Autowired
    ClickStreamServiceImpl clickStreamService;

    @PostMapping("/clickstream/singlerecord")
    public ResponseEntity<?> postClickStreamRecord(@RequestBody ClickStreamRecord clickStreamRecord) {
        String clickEvent = clickStreamService.clickStreamString(clickStreamRecord);
        if (clickEvent != null) {
            System.out.println(clickEvent);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }
}
