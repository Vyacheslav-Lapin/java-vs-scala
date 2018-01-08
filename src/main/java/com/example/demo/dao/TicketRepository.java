package com.example.demo.dao;

import com.example.demo.model.Ticket;
import io.vavr.control.Either;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Repository
public class TicketRepository {

    public CompletableFuture<Either<NoTicketsForUserException, Collection<Ticket>>> byUserId(long userId) {
        return CompletableFuture.supplyAsync(() -> userId == 0 ?
                Either.left(new NoTicketsForUserException(userId)) :
                Either.right(Arrays.asList(
                        new Ticket(userId + 100, "It`s my first ticket!"),
                        new Ticket(userId + 101, "It`s my second ticket!"))));
    }
}
