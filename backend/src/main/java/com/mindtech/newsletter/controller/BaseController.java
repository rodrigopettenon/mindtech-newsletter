package com.mindtech.newsletter.controller;

import com.mindtech.newsletter.dto.StandardObjectReturn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class BaseController {

    protected ResponseEntity<StandardObjectReturn> createObjectReturn(Object object) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new StandardObjectReturn(Instant.now(), HttpStatus.CREATED.value() , null, object));
    }

    protected ResponseEntity<StandardObjectReturn> successObjectReturn(Object object) {
        return ResponseEntity.ok()
                .body(new StandardObjectReturn(Instant.now(), HttpStatus.OK.value(), null, object));
    }
}
