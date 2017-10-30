package dev.local.taskmgr.mongo.services;

import dev.local.taskmgr.mongo.domain.Quote;
import java.io.IOException;

public interface QuoteService {
    Quote getDailyQuote() throws IOException;
}
