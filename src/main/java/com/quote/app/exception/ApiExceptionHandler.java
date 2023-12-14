package com.quote.app.exception;

import com.quote.app.payload.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse> catchResourceNotFoundException(ResourceNotFound e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> catchIllegalArgumentException(IllegalArgumentException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse> catchIllegalArgumentException(IllegalStateException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
