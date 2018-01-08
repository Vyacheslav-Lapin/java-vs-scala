package com.example.demo.services;

import com.example.demo.dao.EmailNotRegisteredException;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static java.net.URLDecoder.decode;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    @SneakyThrows
    public CompletableFuture<Either<EmailNotRegisteredException, User>> byEmailAddress(String emailAddress) {
        return userRepository.byEmailAddress(
                decode(emailAddress, "UTF-8"));
    }
}
