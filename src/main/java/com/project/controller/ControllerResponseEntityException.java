package com.project.controller;

import com.project.exception.ExceptionResponse;
import com.project.exception.UserGenericsException;
import com.project.exception.UserNotFoundException;
import com.project.exception.UserPasswordWrongException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class ControllerResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserGenericsException.class)
    public final ResponseEntity<Object> handleUserGenericsException(UserGenericsException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserPasswordWrongException.class)
    public final ResponseEntity<Object> handleUserPasswordWrongException(UserPasswordWrongException ex, WebRequest request) {
        return buildResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }

    // BUILD RESPONSE ENTITY TO REDUCE BOILER PLATE
    private ResponseEntity<Object> buildResponseEntity(Exception ex, WebRequest request, HttpStatus status) {
        ExceptionResponse exResp = new ExceptionResponse(new Date(),
                                        ex.getMessage(),
                                        request.getDescription(true));
        return new ResponseEntity<>(exResp, new HttpHeaders(), status);
    }
}
