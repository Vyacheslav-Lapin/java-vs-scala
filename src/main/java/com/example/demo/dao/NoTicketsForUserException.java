package com.example.demo.dao;

import com.example.demo.model.User;

public class NoTicketsForUserException extends RuntimeException {
    public NoTicketsForUserException(User user) {
        super("No tickets for " + user);
    }
}
