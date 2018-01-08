package com.example.demo.services;

import com.example.demo.model.Bonus;
import com.example.demo.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class MarketingService {
    public Collection<Bonus> getBonuses(Ticket tickets) {
        return Arrays.asList(
                new Bonus(1, "New year bonus"),
                new Bonus(2, "Birthday bonus"),
                new Bonus(3, "Random bonus")
        );
    }
}
