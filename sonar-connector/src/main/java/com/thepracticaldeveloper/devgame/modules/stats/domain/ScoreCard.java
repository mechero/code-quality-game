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
    private int totalPoints;
    private int totalPaidDebt;
    private SeverityType severityType;

    public ScoreCard() {
    }

    public ScoreCard(String id, String sonarId, String userId, Instant wonAt, int totalPoints, int totalPaidDebt, SeverityType severityType) {
        this.id = id;
        this.sonarId = sonarId;
        this.userId = userId;
        this.wonAt = wonAt;
        this.totalPoints = totalPoints;
        this.totalPaidDebt = totalPaidDebt;
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

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTotalPaidDebt() {
        return totalPaidDebt;
    }

    public void setTotalPaidDebt(int totalPaidDebt) {
        this.totalPaidDebt = totalPaidDebt;
    }

    public SeverityType getSeverityType() {
        return severityType;
    }

    public void setSeverityType(SeverityType severityType) {
        this.severityType = severityType;
    }

    @Override
    public String toString() {
        return "ScoreCard{" +
                "id='" + id + '\'' +
                ", sonarId='" + sonarId + '\'' +
                ", userId='" + userId + '\'' +
                ", wonAt=" + wonAt +
                ", totalPoints=" + totalPoints +
                ", totalPaidDebt=" + totalPaidDebt +
                ", severityType=" + severityType +
                '}';
    }
}
