package com.quote.app.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "quote")
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime created;

    private LocalDateTime updated;

    @Column(nullable = false)
    private Long score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes;

}
