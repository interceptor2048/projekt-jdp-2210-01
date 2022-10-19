package com.kodilla.ecommercee.errorhandlers;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {}

    public UserNotFoundException(String message) {
        super(message);
    }
}
