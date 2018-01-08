package com.example.demo.dao;

public class NoTicketsForUserException extends RuntimeException {
    NoTicketsForUserException(long userId) {
        super("No tickets for user with id " + userId);
    }

    public NoTicketsForUserException(Exception cause) {
        super("No tickets because of another exception: " + cause.getMessage(), cause);
    }
}
