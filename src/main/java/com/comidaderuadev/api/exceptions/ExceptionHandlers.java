package com.comidaderuadev.api.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(NotFoundException exc) {
        ExceptionResponse error = new ExceptionResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exc,
                                                               HttpHeaders headers,
                                                               HttpStatusCode statusCode,
                                                               WebRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(
                "Total erros: " + exc.getFieldErrorCount()
                + ". First error: " + Objects.requireNonNull(exc.getFieldError()).getDefaultMessage()
        );
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
