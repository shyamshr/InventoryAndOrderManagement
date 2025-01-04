package com.assignment.Flobiz_Assignment.controllers;

import com.assignment.Flobiz_Assignment.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = {ProductController.class,OrderController.class})// can mention particular classes which need to be advised
public class ExceptionAdvices {
    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<String> handleException(ProductNotFoundException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity<String> handleException(OrderNotFoundException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({OutOfStockException.class})
    public ResponseEntity<String> handleException(OutOfStockException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({InvalidDataException.class})
    public ResponseEntity<String> handleException(InvalidDataException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e){
        return  new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
