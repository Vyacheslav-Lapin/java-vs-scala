package com.example.demo.dao;

import com.example.demo.model.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.time.Month.NOVEMBER;

@Repository
public class UserRepository {

    private Map<String, User> userMap = new HashMap<>();

    public UserRepository() {
        put(new User(
                1,
                "Vasya",
                "Pupkin",
                LocalDate.of(1985, NOVEMBER, 24),
                "Vasya.Pupkin@ma.il"));
    }

    private void put(User... users) {
        for (User user : users)
            userMap.put(user.getEmailAddress(), user);
    }

    @SneakyThrows
    public Optional<User> byEmailAddress(String emailAddress) {
        return Optional.ofNullable(userMap.get(emailAddress));
    }
}
