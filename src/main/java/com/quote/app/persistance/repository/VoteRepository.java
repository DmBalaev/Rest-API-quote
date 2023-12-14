package com.quote.app.persistance.repository;

import com.quote.app.persistance.entity.Quote;
import com.quote.app.persistance.entity.User;
import com.quote.app.persistance.entity.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByQuoteAndOwner(Quote quote, User user);
    List<Vote> findByQuoteId(Long quoteId, Pageable pageable);
    Optional<Vote> findByQuoteAndOwner(Quote quote, User user);
}
