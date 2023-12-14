package com.quote.app.payload.responses;

import com.quote.app.persistance.entity.enums.VoteType;

public record VoteDto(
        VoteType type,
        Long ownerId,
        Long quoteId
) {
}
