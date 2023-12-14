package com.quote.app.controller;

import com.quote.app.payload.responses.ApiResponse;
import com.quote.app.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/votes")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping("/vote/{quoteId}")
    public ResponseEntity<ApiResponse> vote(
            @PathVariable Long quoteId,
            @RequestParam String voteType,
            @RequestParam Optional<String> principal) {
        ApiResponse response = voteService.vote(
                quoteId, voteType,
                principal.orElseThrow(() -> new RuntimeException("You are not authorized")));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cancel/{quoteId}")
    public ResponseEntity<ApiResponse> cancelVote(
            @PathVariable Long quoteId,
            @RequestParam Optional<String> principal) {
        ApiResponse response = voteService.cancel(
                quoteId,
                principal.orElseThrow(() -> new RuntimeException("You are not authorized")));
        return ResponseEntity.ok(response);
    }
}