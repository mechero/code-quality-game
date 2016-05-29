package es.macero.cqgame.modules.stats.domain;

import es.macero.cqgame.modules.badges.domain.SonarBadge;

import java.util.Collection;
import java.util.Collections;

public final class SonarStatsRowBuilder {

    private String userAlias;
    private String userTeam;
    private int totalPoints = 0;
    private int totalPaidDebt = 0;
    private int blocker = 0;
    private int critical = 0;
    private int major = 0;
    private int minor = 0;
    private int info = 0;
    private Collection<SonarBadge> badges = Collections.emptyList();

    public SonarStatsRowBuilder(String userAlias, String userTeam) {
        this.userAlias = userAlias;
        this.userTeam = userTeam;
    }

    public SonarStatsRowBuilder withTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
        return this;
    }

    public SonarStatsRowBuilder withTotalPaidDebt(int totalPaidDebt) {
        this.totalPaidDebt = totalPaidDebt;
        return this;
    }

    public SonarStatsRowBuilder withBlocker(int blocker) {
        this.blocker = blocker;
        return this;
    }

    public SonarStatsRowBuilder withCritical(int critical) {
        this.critical = critical;
        return this;
    }

    public SonarStatsRowBuilder withMajor(int major) {
        this.major = major;
        return this;
    }

    public SonarStatsRowBuilder withMinor(int minor) {
        this.minor = minor;
        return this;
    }

    public SonarStatsRowBuilder withInfo(int info) {
        this.info = info;
        return this;
    }

    public SonarStatsRowBuilder withBadges(Collection<SonarBadge> badges) {
        this.badges = badges;
        return this;
    }

    public SonarStatsRow createSonarStatsRow() {
        return new SonarStatsRow(userAlias, userTeam, totalPoints, totalPaidDebt, blocker, critical, major, minor, info, badges);
    }
}