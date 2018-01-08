package com.example.demo.dao;

import com.example.demo.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Repository
public class TicketRepository {

    public Collection<Ticket> byUserId(long userId) {
        return userId == 0 ? Collections.emptySet() : Arrays.asList(
                new Ticket(userId + 100, "It`s my first ticket!"),
                new Ticket(userId + 101, "It`s my second ticket!"));
    }
}
