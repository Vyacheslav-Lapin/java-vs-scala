package com.example.demo.dao;

import com.example.demo.model.User;
import io.vavr.control.Either;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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

    public CompletableFuture<Either<EmailNotRegisteredException, User>> byEmailAddress(String emailAddress) {
        return CompletableFuture.supplyAsync(() -> userMap.get(emailAddress))
                .thenApply(Optional::ofNullable)
                .thenApply(optionalUser -> optionalUser
                        .map(Either::<EmailNotRegisteredException, User>right)
                        .orElse(Either.<EmailNotRegisteredException, User>left(
                                new EmailNotRegisteredException(emailAddress))));
    }
}
