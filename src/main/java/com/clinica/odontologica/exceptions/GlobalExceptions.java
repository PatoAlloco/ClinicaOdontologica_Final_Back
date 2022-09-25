package com.clinica.odontologica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> procesamientoResourceNotFoundException(ResourceNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> procesamientoBadRequestException( BadRequestException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
