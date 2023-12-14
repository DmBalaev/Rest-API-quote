package com.quote.app.persistance.entity;

import com.quote.app.persistance.entity.enums.VoteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "vote")
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VoteType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;
}
