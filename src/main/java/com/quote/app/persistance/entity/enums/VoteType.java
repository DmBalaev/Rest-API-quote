package com.quote.app.persistance.entity.enums;

public enum VoteType {
    LIKE,
    DISLIKE;

    public static VoteType convert(String type) {
        try {
            return VoteType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid VoteType value");
        }
    }
}
