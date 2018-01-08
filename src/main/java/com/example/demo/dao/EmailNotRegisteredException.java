package com.example.demo.dao;

public class EmailNotRegisteredException extends RuntimeException {

    public EmailNotRegisteredException(String email) {
        super(String.format("User with email %s is not registered in the system!", email));
    }
}
