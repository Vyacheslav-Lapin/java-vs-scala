package com.example.demo.services;

import com.example.demo.model.Bonus;
import com.example.demo.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Service
public class MarketingService {
    public CompletableFuture<Collection<Bonus>> getBonuses(Collection<Ticket> tickets) {
        return CompletableFuture.supplyAsync(() -> Arrays.asList(
                new Bonus(1, "New year 10% bonus for tickets " + tickets),
                new Bonus(2, "Birthday 15% bonus for tickets " + tickets),
                new Bonus(3, "Random 5% bonus for tickets " + tickets)));
    }
}
