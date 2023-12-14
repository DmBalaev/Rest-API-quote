package com.quote.app.persistance.util;

import com.quote.app.payload.responses.VoteDto;
import com.quote.app.persistance.entity.Quote;
import com.quote.app.persistance.entity.User;
import com.quote.app.persistance.entity.Vote;
import com.quote.app.persistance.entity.enums.VoteType;
import com.quote.app.util.VoteMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VoteMapperTest {
    private final VoteMapper voteMapper = new VoteMapper();

    @Test
    void apply_shouldMapVoteToVoteDto() {
        User user = User.builder().id(1L).name("John Doe").email("john@example.com").build();
        Quote quote = Quote.builder().id(2L).content("A great quote").build();
        Vote vote = Vote.builder().type(VoteType.LIKE).owner(user).quote(quote).build();

        VoteDto voteDto = voteMapper.apply(vote);

        assertThat(voteDto.type()).isEqualTo(VoteType.LIKE);
        assertThat(voteDto.ownerId()).isEqualTo(1L);
        assertThat(voteDto.quoteId()).isEqualTo(2L);
    }
}