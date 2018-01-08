package com.example.demo.dao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User with this email is not registered in the system!")
public class EmailNotRegisteredException extends RuntimeException {

    EmailNotRegisteredException(String email) {
        super(String.format("User with email %s is not registered in the system!", email));
    }
}
