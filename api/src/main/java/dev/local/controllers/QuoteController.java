package dev.local.controllers;

import dev.local.helpers.CacheFor;
import dev.local.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.MINUTES;

@RestController
@RequestMapping("/quotes")
public class QuoteController {
    private QuoteService service;

    @Autowired
    public QuoteController(QuoteService service) {
        this.service = service;
    }

    @CacheFor(amount=10, unit = MINUTES)
    @RequestMapping(value = "/daily", method = RequestMethod.GET)
    public ResponseEntity<?> getQuote() {
        try {
            return ResponseEntity.ok(service.getDailyQuote());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
