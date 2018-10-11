package com.thepracticaldeveloper.devgame.modules.stats.domain;

public enum SeverityType {
    BLOCKER(20), CRITICAL(10), MAJOR(5), MINOR(2), INFO(1);

    private final int score;

    SeverityType(final int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
