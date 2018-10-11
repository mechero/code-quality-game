package com.thepracticaldeveloper.devgame.modules.badges.domain;

public enum BadgeDetails {

    EARLY_BIRD("Early Bird", "You're an early adopter of the game"),
    UNIT_TESTER_PAPER("Paper Unit Tester", "You covered legacy code with at least 1 Unit Test"),
    UNIT_TESTER_BRONZE("Bronze Unit Tester", "You covered legacy code with at least 10 Unit Tests"),
    UNIT_TESTER_SILVER("Silver Unit Tester", "You covered legacy code with at least 25 Unit Tests"),
    UNIT_TESTER_GOLD("Gold Unit Tester", "You covered legacy code with at least 100 Unit Tests");

    private final String name;
    private final String description;

    BadgeDetails(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
