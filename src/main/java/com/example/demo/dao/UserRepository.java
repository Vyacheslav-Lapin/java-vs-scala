package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import static java.time.Month.NOVEMBER;

@Repository
public class UserRepository {
    public User byEmailAddress(String emailAddress) {
        return new User(
                1,
                "Vasya",
                "Pupkin",
                LocalDate.of(1985, NOVEMBER, 24),
                emailAddress);
    }
}
