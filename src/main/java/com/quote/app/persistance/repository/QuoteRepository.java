package com.quote.app.persistance.repository;

import com.quote.app.persistance.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findTop10ByOrderByScoreDesc();
    List<Quote> findTop10ByOrderByVotesAsc();
    @Query(value = "SELECT * FROM Quote ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Quote> findRandomQuote();


}
