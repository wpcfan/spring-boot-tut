package dev.local.taskmgr.services;


import dev.local.taskmgr.domain.Quote;

import java.io.IOException;

public interface QuoteService {
    Quote getDailyQuote() throws IOException;
}
