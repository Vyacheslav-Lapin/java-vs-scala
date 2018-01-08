package com.example.demo.model;

import lombok.Value;

import java.time.LocalDate;

@Value
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String emailAddress;
}
