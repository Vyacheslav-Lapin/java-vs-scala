package com.example.demo.controllers;

import com.example.demo.dao.TicketRepository;
import com.example.demo.model.Bonus;
import com.example.demo.model.User;
import com.example.demo.services.MarketingService;
import com.example.demo.services.UserService;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
    public Mono<UserData> userData(@RequestParam("emailAddress") String emailAddress) {
//    public UserData userData(@RequestParam("emailAddress") String emailAddress) {
        return
                Mono.fromFuture(
                userService.byEmailAddress(emailAddress).thenCompose(eUser ->
                        eUser.fold(e -> CompletableFuture.completedFuture(Either.left(e)), user ->
                                ticketRepository.byUserId(user.getId()).thenCompose(eTickets ->
                                        eTickets.<CompletableFuture<Either<RuntimeException, UserData>>>fold(e ->
                                                        CompletableFuture.completedFuture(Either.left(e)),
                                                tickets -> marketingService.getBonuses(tickets).thenApply(bonuses ->
                                                        Either.right(new UserData(user, bonuses)))))))
                        .thenApply(either -> {
                            if (either.isLeft()) throw either.getLeft();
                            else return either.get();
                        })
//                        .get()
        )
                ;
    }

    @Value
    private class UserData {
        private User user;
        private Collection<Bonus> bonuses;
    }
}
