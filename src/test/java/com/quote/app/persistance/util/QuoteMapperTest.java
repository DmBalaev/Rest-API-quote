package com.quote.app.persistance.util;

import com.quote.app.payload.responses.QuoteDto;
import com.quote.app.persistance.entity.Quote;
import com.quote.app.persistance.entity.User;
import com.quote.app.util.QuoteMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QuoteMapperTest {
    private final QuoteMapper quoteMapper = new QuoteMapper();

    @Test
    void apply_shouldMapQuoteToQuoteDto() {
        User creator = User.builder().name("John Doe").email("john@example.com").build();
        Quote quote = Quote.builder().id(1L).content("A great quote").creator(creator).build();

        QuoteDto quoteDto = quoteMapper.apply(quote);

        assertThat(quoteDto.id()).isEqualTo(1L);
        assertThat(quoteDto.text()).isEqualTo("A great quote");
        assertThat(quoteDto.emailCreator()).isEqualTo("john@example.com");
    }
}