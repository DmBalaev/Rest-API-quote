package com.quote.app.service;

import com.quote.app.exception.ResourceNotFound;
import com.quote.app.payload.responses.ApiResponse;
import com.quote.app.persistance.entity.Quote;
import com.quote.app.persistance.entity.User;
import com.quote.app.persistance.entity.Vote;
import com.quote.app.persistance.entity.enums.VoteType;
import com.quote.app.persistance.repository.QuoteRepository;
import com.quote.app.persistance.repository.UserRepository;
import com.quote.app.persistance.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    @Transactional
    public ApiResponse vote(Long quoteId, String voteType, String userEmail){
        var quote = findQuote(quoteId);
        var user = findUser(userEmail);
        var type = VoteType.convert(voteType);
        Optional<Vote> existingVote = voteRepository.findByQuoteAndOwner(quote, user);

        if (existingVote.isPresent()) {
            if (existingVote.get().getType().equals(type)) {
                throw new IllegalStateException("User has already voted with the same VoteType on this quote");
            } else {
                updateQuoteScore(quote, Optional.of(existingVote.get().getType()), type);
                existingVote.get().setType(type);
                voteRepository.save(existingVote.get());
            }
        } else {
            var vote = Vote.builder()
                    .owner(user)
                    .type(type)
                    .quote(quote)
                    .build();
            voteRepository.save(vote);
            updateQuoteScore(quote, Optional.empty(), type);
        }

        return new ApiResponse("You successfully voted");
    }

    @Transactional
    public ApiResponse cancel(Long quoteId, String userEmail){
        var quote = findQuote(quoteId);
        var user = findUser(userEmail);
        var existingVote = voteRepository.findByQuoteAndOwner(quote, user)
                .orElseThrow(() -> new ResourceNotFound("Vote not found"));

        voteRepository.delete(existingVote);

        return new ApiResponse("Vote canceled successfully");
    }

    private User findUser(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFound("User not found"));
    }

    private Quote findQuote(Long quoteId) {
        var quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new ResourceNotFound("Quote not found"));
        return quote;
    }

    private void updateQuoteScore(Quote quote, Optional<VoteType> currentType, VoteType newType) {
        Long scoreChange = 0L;

        if (currentType.isPresent()) {
            VoteType type = currentType.get();
            scoreChange = type.equals(VoteType.LIKE) ? -2L : 2L;
        }else {
            scoreChange += newType.equals(VoteType.LIKE) ? 1L : -1L;
        }

        Long newScore = quote.getScore() + scoreChange;
        quote.setScore(newScore);
        quoteRepository.save(quote);
    }
}
