package com.example.demo.controllers;

import com.example.demo.dao.EmailNotRegisteredException;
import com.example.demo.dao.NoTicketsForUserException;
import com.example.demo.dao.TicketRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.Bonus;
import com.example.demo.model.User;
import com.example.demo.services.MarketingService;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static java.net.URLDecoder.decode;
import static lombok.AccessLevel.NONE;

@Slf4j
@Value
@Getter(NONE)
@RestController
public class UserDataController {

    private UserRepository userRepository;

    private TicketRepository ticketRepository;

    private MarketingService marketingService;

    @SneakyThrows
    @GetMapping("/userData")
    public UserData userData(@RequestParam("emailAddress") String emailAddress) {

        val user = userRepository.byEmailAddress(decode(emailAddress, "UTF-8"))
                .orElseThrow(() -> new EmailNotRegisteredException(emailAddress));

        val tickets = ticketRepository.byUserId(user.getId());
        if (tickets.isEmpty())
            throw new NoTicketsForUserException(user);

        return new UserData(user, marketingService.getBonuses(tickets));
    }

    @Value
    private class UserData {
        private User user;
        private Collection<Bonus> bonuses;
    }
}
