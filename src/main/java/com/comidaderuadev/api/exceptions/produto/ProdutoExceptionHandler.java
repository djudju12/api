package com.comidaderuadev.api.exceptions.produto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProdutoExceptionHandler {
 
    @ExceptionHandler
    public ResponseEntity<ProdutoExceptionResponse> handleException(ProdutoNotFoundException exc) {
        ProdutoExceptionResponse error = new ProdutoExceptionResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ProdutoExceptionResponse>(error, HttpStatus.NOT_FOUND);
    
    }

    @ExceptionHandler
    public ResponseEntity<ProdutoExceptionResponse> handleException(Exception exc) {
        ProdutoExceptionResponse error = new ProdutoExceptionResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ProdutoExceptionResponse>(error, HttpStatus.BAD_REQUEST);
    }
}
