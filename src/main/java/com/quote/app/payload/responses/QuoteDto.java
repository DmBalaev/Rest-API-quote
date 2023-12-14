package com.quote.app.payload.responses;

public record QuoteDto(
        Long id,
        String text,
        String emailCreator,
        Long score
) {
}
