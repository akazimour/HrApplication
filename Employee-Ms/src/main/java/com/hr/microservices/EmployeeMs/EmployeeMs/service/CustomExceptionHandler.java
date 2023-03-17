package com.hr.microservices.EmployeeMs.EmployeeMs.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NoneUniqueIdException.class)
    public ResponseEntity<MyError> handleNonUniqueId(NoneUniqueIdException e, WebRequest req) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MyError(e.getMessage(), 400));
    }
}
