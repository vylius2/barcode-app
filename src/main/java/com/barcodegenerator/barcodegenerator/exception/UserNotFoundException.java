package com.barcodegenerator.barcodegenerator.exception;

public class UserNotFoundException extends RuntimeException{
    String username;

    public UserNotFoundException(String username) {
        this.username = username;
    }
}
