package com.quote.app.util;

import com.quote.app.payload.responses.VoteDto;
import com.quote.app.persistance.entity.Vote;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class VoteMapper implements Function<Vote, VoteDto> {
    @Override
    public VoteDto apply(Vote vote) {
        return new VoteDto(
                vote.getType(),
                vote.getOwner().getId(),
                vote.getQuote().getId()
        );
    }
}
