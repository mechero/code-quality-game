package com.thepracticaldeveloper.devgame.modules.stats.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "score-cards")
public class ScoreCard {

    @Id
    private String id;
    @Indexed
    private String sonarId;
    @Indexed
    private String userId;

    private Instant wonAt;
    private int score;
    private int paidDebtMinutes;
    private SeverityType severityType;

    public ScoreCard() {
    }

    public ScoreCard(String id, String sonarId, String userId, Instant wonAt, int score, int paidDebtMinutes, SeverityType severityType) {
        this.id = id;
        this.sonarId = sonarId;
        this.userId = userId;
        this.wonAt = wonAt;
        this.score = score;
        this.paidDebtMinutes = paidDebtMinutes;
        this.severityType = severityType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSonarId() {
        return sonarId;
    }

    public void setSonarId(String sonarId) {
        this.sonarId = sonarId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getWonAt() {
        return wonAt;
    }

    public void setWonAt(Instant wonAt) {
        this.wonAt = wonAt;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPaidDebtMinutes() {
        return paidDebtMinutes;
    }

    public void setPaidDebtMinutes(int paidDebtMinutes) {
        this.paidDebtMinutes = paidDebtMinutes;
    }

    public SeverityType getSeverityType() {
        return severityType;
    }

    public void setSeverityType(SeverityType severityType) {
        this.severityType = severityType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScoreCard scoreCard = (ScoreCard) o;

        if (score != scoreCard.score) return false;
        if (paidDebtMinutes != scoreCard.paidDebtMinutes) return false;
        if (id != null ? !id.equals(scoreCard.id) : scoreCard.id != null) return false;
        if (sonarId != null ? !sonarId.equals(scoreCard.sonarId) : scoreCard.sonarId != null) return false;
        if (userId != null ? !userId.equals(scoreCard.userId) : scoreCard.userId != null) return false;
        if (wonAt != null ? !wonAt.equals(scoreCard.wonAt) : scoreCard.wonAt != null) return false;
        return severityType == scoreCard.severityType;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sonarId != null ? sonarId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (wonAt != null ? wonAt.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + paidDebtMinutes;
        result = 31 * result + (severityType != null ? severityType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScoreCard{" +
                "id='" + id + '\'' +
                ", sonarId='" + sonarId + '\'' +
                ", userId='" + userId + '\'' +
                ", wonAt=" + wonAt +
                ", score=" + score +
                ", paidDebtMinutes=" + paidDebtMinutes +
                ", severityType=" + severityType +
                '}';
    }
}
