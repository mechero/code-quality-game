package com.thepracticaldeveloper.devgame.modules.stats.domain;

import com.thepracticaldeveloper.devgame.modules.badges.domain.SonarBadge;

import java.util.ArrayList;
import java.util.Collection;

public final class SonarStatsRow {

    private String userAlias;
    private String userTeam;
    private int totalPoints;
    private int totalPaidDebt;
    private int blocker;
    private int critical;
    private int major;
    private int minor;
    private int info;
    private Collection<SonarBadge> badges;

    public SonarStatsRow(String userAlias, String userTeam, int totalPoints, int totalPaidDebt, int blocker, int critical, int major, int minor, int info, Collection<SonarBadge> badges) {
        super();
        this.userAlias = userAlias;
        this.userTeam = userTeam;
        this.totalPoints = totalPoints;
        this.totalPaidDebt = totalPaidDebt;
        this.blocker = blocker;
        this.critical = critical;
        this.major = major;
        this.minor = minor;
        this.info = info;
        this.badges = badges;
    }

    public SonarStatsRow(final String userAlias, final String userTeam) {
        this(userAlias, userTeam, 0, 0, 0, 0, 0, 0, 0, new ArrayList<>());
    }

    public void addTotalPoints(int points) {
        this.totalPoints += points;
    }

    public void addPaidDebt(int debt) {
        this.totalPaidDebt += debt;
    }

    public void addBlocker(int blocker) {
        this.blocker += blocker;
    }

    public void addCritical(int critical) {
        this.critical = critical;
    }

    public void addMajor(int major) {
        this.major = major;
    }

    public void addMinor(int minor) {
        this.minor = minor;
    }

    public void addInfo(int info) {
        this.info = info;
    }

    public void addBadge(SonarBadge badge) {
        this.badges.add(badge);
    }

    public String getUserAlias() {
        return userAlias;
    }

    public String getUserTeam() {
        return userTeam;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getTotalPaidDebt() {
        return totalPaidDebt;
    }

    public int getBlocker() {
        return blocker;
    }

    public int getCritical() {
        return critical;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getInfo() {
        return info;
    }

    public Collection<SonarBadge> getBadges() {
        return badges;
    }

}
