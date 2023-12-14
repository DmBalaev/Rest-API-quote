package com.quote.app.controller;

import com.quote.app.payload.responses.QuoteDto;
import com.quote.app.payload.requests.QuoteRequest;
import com.quote.app.payload.requests.QuoteUpdateRequest;
import com.quote.app.payload.responses.ApiResponse;
import com.quote.app.service.QuoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/quotes")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;

    @PostMapping("/add")
    public ResponseEntity<?> addQuote(
            @RequestBody QuoteRequest request,
            @RequestParam Optional<String> principal) {
        QuoteDto createdQuote = quoteService.add(
                request,
                principal.orElseThrow(() -> new RuntimeException("You are not authorized")));
        return new ResponseEntity<>(createdQuote, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<QuoteDto> updateQuote(
            @RequestBody QuoteUpdateRequest request,
            @RequestParam Optional<String> principal) {
        QuoteDto updatedQuote = quoteService.update(
                request,
                principal.orElseThrow(() -> new RuntimeException("You are not authorized")));
        return ResponseEntity.ok(updatedQuote);
    }

    @DeleteMapping("/delete/{quoteId}")
    public ResponseEntity<ApiResponse> deleteQuote(
            @PathVariable Long quoteId,
            @RequestParam Optional<String> principal) {
        ApiResponse response = quoteService.delete(
                quoteId,
                principal.orElseThrow(() -> new RuntimeException("You are not authorized")));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top")
    public ResponseEntity<List<QuoteDto>> getTopQuotes() {
        List<QuoteDto> topQuotes = quoteService.getTopQuotes();
        return ResponseEntity.ok(topQuotes);
    }

    @GetMapping("/bottom")
    public ResponseEntity<List<QuoteDto>> getBottomQuotes() {
        List<QuoteDto> bottomQuotes = quoteService.getBottomQuotes();
        return ResponseEntity.ok(bottomQuotes);
    }

    @GetMapping("/random")
    public ResponseEntity<QuoteDto> getRandomQuote() {
        QuoteDto randomQuote = quoteService.getRandomQuote();
        return ResponseEntity.ok(randomQuote);
    }
}