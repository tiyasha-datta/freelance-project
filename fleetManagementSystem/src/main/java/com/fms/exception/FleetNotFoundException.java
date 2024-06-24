package com.fms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FleetNotFoundException extends RuntimeException {

    public FleetNotFoundException(String message) {
        super(message);
    }
}
