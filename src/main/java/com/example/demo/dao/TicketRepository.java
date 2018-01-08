package com.example.demo.dao;

import com.example.demo.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;

@Repository
public class TicketRepository {

    public Collection<Ticket> byUserId(long userId) {
        return Arrays.asList(
                new Ticket(userId + 100, "It`s my first ticket!"),
                new Ticket(userId + 101, "It`s my second ticket!"));
    }
}
