package com.thepracticaldeveloper.devgame.modules.stats.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "badge-cards")
public class BadgeCard {

    @Id
    private String id;
    @Indexed
    private String userId;
    @Indexed
    private String badgeKey;
    private Instant wonAt;
    private int score;

    public BadgeCard() {
    }

    public BadgeCard(String id, String userId, String badgeKey, Instant wonAt, int score) {
        this.id = id;
        this.userId = userId;
        this.badgeKey = badgeKey;
        this.wonAt = wonAt;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getBadgeKey() {
        return badgeKey;
    }

    public Instant getWonAt() {
        return wonAt;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BadgeCard badgeCard = (BadgeCard) o;

        if (score != badgeCard.score) return false;
        if (id != null ? !id.equals(badgeCard.id) : badgeCard.id != null) return false;
        if (userId != null ? !userId.equals(badgeCard.userId) : badgeCard.userId != null) return false;
        if (badgeKey != null ? !badgeKey.equals(badgeCard.badgeKey) : badgeCard.badgeKey != null) return false;
        return wonAt != null ? wonAt.equals(badgeCard.wonAt) : badgeCard.wonAt == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (badgeKey != null ? badgeKey.hashCode() : 0);
        result = 31 * result + (wonAt != null ? wonAt.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }

    @Override
    public String toString() {
        return "BadgeCard{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", badgeKey='" + badgeKey + '\'' +
                ", wonAt=" + wonAt +
                ", score=" + score +
                '}';
    }
}
