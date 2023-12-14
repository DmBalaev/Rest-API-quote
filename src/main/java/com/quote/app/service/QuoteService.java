package com.quote.app.service;

import com.quote.app.exception.InsufficientPermissionsException;
import com.quote.app.exception.ResourceNotFound;
import com.quote.app.payload.requests.QuoteRequest;
import com.quote.app.payload.requests.QuoteUpdateRequest;
import com.quote.app.payload.responses.ApiResponse;
import com.quote.app.payload.responses.QuoteDto;
import com.quote.app.persistance.entity.Quote;
import com.quote.app.persistance.repository.QuoteRepository;
import com.quote.app.persistance.repository.UserRepository;
import com.quote.app.util.QuoteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final QuoteMapper quoteMapper;

    public QuoteDto add(QuoteRequest request,String userEmail){
        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFound("User not found"));
        var quote = Quote.builder()
                .content(request.getContent())
                .created(LocalDateTime.now())
                .score(0L)
                .creator(user)
                .build();

        quoteRepository.save(quote);
        log.info("created quote by {}",user.getName());
        return quoteMapper.apply(quote);
    }

    public QuoteDto update(QuoteUpdateRequest request, String userEmail){
        var quote = getQuote(request.getId());
        if (isNotOwner(userEmail, quote)){
            throw  new InsufficientPermissionsException("Insufficient permissions to perform the requested operation.");
        }
        quote.setContent(request.getContent());
        quote.setUpdated(LocalDateTime.now());
        quoteRepository.save(quote);

        log.info("update quote:{}",quote.getId());
        return quoteMapper.apply(quote);
    }

    public ApiResponse delete(Long quoteId, String userEmail){
        var quote = getQuote(quoteId);
        if (isNotOwner(userEmail, quote)){
            throw  new InsufficientPermissionsException("Insufficient permissions to perform the requested operation.");
        }
        quoteRepository.deleteById(quoteId);
        log.info("delete quote:{}",quote.getId());
        return new ApiResponse("You successfully delete quote");
    }

    public List<QuoteDto> getTopQuotes() {
        List<Quote> topQuotes = quoteRepository.findTop10ByOrderByScoreDesc();

        return topQuotes.stream()
                .map(quoteMapper)
                .toList();
    }

    public List<QuoteDto> getBottomQuotes() {
        List<Quote> bottomQuotes = quoteRepository.findTop10ByOrderByVotesAsc();
        return bottomQuotes.stream()
                .map(quoteMapper)
                .toList();
    }

    public QuoteDto getRandomQuote(){
        var quote = quoteRepository.findRandomQuote()
                .orElseThrow(() -> new ResourceNotFound("Quote not found"));

        return quoteMapper.apply(quote);
    }

    private Quote getQuote(Long quoteId) {
        return quoteRepository.findById(quoteId)
                .orElseThrow(() -> new ResourceNotFound("Quote not found"));
    }

    private boolean isNotOwner(String userEmail, Quote quote){
        return !userEmail.equals(quote.getCreator().getEmail());
    }
}
