package com.example.demo.controllers;

import com.example.demo.dao.NoTicketsForUserException;
import com.example.demo.dao.TicketRepository;
import com.example.demo.model.Bonus;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.services.MarketingService;
import com.example.demo.services.UserService;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Slf4j
@AllArgsConstructor
@RestController
public class UserDataController {

    private UserService userService;

    private TicketRepository ticketRepository;

    private MarketingService marketingService;

    @SneakyThrows
    @GetMapping("/userData")
    public UserData userData(@RequestParam("emailAddress") String emailAddress) {

        val eitherUserCompletableFuture = userService.byEmailAddress(emailAddress);

        CompletableFuture<Either<NoTicketsForUserException, Collection<Ticket>>> tickets = eitherUserCompletableFuture
                .thenApply(eitherUser -> eitherUser.map(User::getId))
                .thenCompose(eitherUserId -> eitherUserId.isLeft() ?
                        CompletableFuture.supplyAsync(() ->
                                Either.left(new NoTicketsForUserException(eitherUserId.getLeft()))) :
                        ticketRepository.byUserId(eitherUserId.get()));

        val user = eitherUserCompletableFuture.get();
        if (user.isLeft())
            throw user.getLeft();

        val bonuses = marketingService.getBonuses(tickets.get().get());
        return new UserData(user.get(), bonuses.get());
    }

    @Value
    private class UserData {
        private User user;
        private Collection<Bonus> bonuses;
    }
}
