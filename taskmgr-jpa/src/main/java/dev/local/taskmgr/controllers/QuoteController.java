package dev.local.taskmgr.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.MINUTES;

import dev.local.taskmgr.helpers.CacheFor;
import dev.local.taskmgr.services.QuoteService;

@RestController
@RequestMapping("/quotes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class QuoteController {
    private final QuoteService service;

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
