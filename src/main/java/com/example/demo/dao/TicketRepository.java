package com.example.demo.dao;

import com.example.demo.model.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {

    public Ticket byUserId(long id) {
        return new Ticket(id + 100, "It`s my ticket!");
    }
}
