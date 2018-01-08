package com.example.demo.controllers;

import com.example.demo.dao.TicketRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.Bonus;
import com.example.demo.model.User;
import com.example.demo.services.MarketingService;
import lombok.Value;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserDataController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    MarketingService marketingService;

    @GetMapping(value = "/userData", produces = APPLICATION_JSON_VALUE)
    public UserData userData(@RequestParam("emailAddress") String emailAddress) {
        val user = userRepository.byEmailAddress(emailAddress);
        val tickets = ticketRepository.byUserId(user.getId());
        val bonuses = marketingService.getBonuses(tickets);
        return new UserData(user, bonuses);
    }
    
    @Value
    class UserData {
        private User user;
        private Collection<Bonus> bonuses;
    }
}
