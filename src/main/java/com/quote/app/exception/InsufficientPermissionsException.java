package com.quote.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InsufficientPermissionsException extends RuntimeException{
    public InsufficientPermissionsException(String message) {
        super(message);
    }
}
