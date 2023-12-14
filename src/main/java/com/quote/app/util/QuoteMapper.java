package com.quote.app.util;

import com.quote.app.payload.responses.QuoteDto;
import com.quote.app.persistance.entity.Quote;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class QuoteMapper implements Function<Quote, QuoteDto> {
    @Override
    public QuoteDto apply(Quote quote) {
        return new QuoteDto(
                quote.getId(),
                quote.getContent(),
                quote.getCreator().getEmail(),
                quote.getScore()
        );
    }
}
