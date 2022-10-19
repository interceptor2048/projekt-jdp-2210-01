package com.kodilla.ecommercee.errorhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Object> handleCartNotFoundException(CartNotFoundException exception){
        return new ResponseEntity<>("Cart with given id does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartOrProductNotFoundException.class)
    public ResponseEntity<Object> handleCartOrProductNotFoundException(CartOrProductNotFoundException exception){
        return new ResponseEntity<>("Cart or product with given id's does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<Object> handleCartOrProductNotFoundException(GroupNotFoundException exception) {
        return new ResponseEntity<>("Group with given id's does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
