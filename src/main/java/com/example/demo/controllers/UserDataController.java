package com.example.demo.controllers;

import com.example.demo.dao.TicketRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.Bonus;
import com.example.demo.model.User;
import com.example.demo.services.MarketingService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;

@Slf4j
@Value
@RestController
public class UserDataController {

    private UserRepository userRepository;

    private TicketRepository ticketRepository;

    private MarketingService marketingService;

    @GetMapping("/userData")
    public UserData userData(@RequestParam("emailAddress") String emailAddress) {

        // TODO: 08/01/2018 extract converting to SpringAOP aspect
        try {
            emailAddress = URLDecoder.decode(emailAddress, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }

        val user = userRepository.byEmailAddress(emailAddress);
        val tickets = ticketRepository.byUserId(user.getId());
        val bonuses = marketingService.getBonuses(tickets);
        return new UserData(user, bonuses);
    }
    
    @Value
    private class UserData {
        private User user;
        private Collection<Bonus> bonuses;
    }
}
