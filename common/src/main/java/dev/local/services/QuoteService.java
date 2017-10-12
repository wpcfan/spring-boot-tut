package dev.local.services;

import dev.local.domain.Quote;

import java.io.IOException;

public interface QuoteService {
    Quote getDailyQuote() throws IOException;
}
