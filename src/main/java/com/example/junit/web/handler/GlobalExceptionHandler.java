package com.example.junit.web.handler;

import com.example.junit.web.dto.response.CMResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> apiException(RuntimeException e) {
        return new ResponseEntity<>(CMResponseDto.builder().code(1).msg(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
