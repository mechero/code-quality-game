package es.macero.cqgame.domain.stats;

import es.macero.cqgame.domain.badges.SonarBadge;

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

    SonarStatsRow(String userAlias, String userTeam, int totalPoints, int totalPaidDebt, int blocker, int critical, int major, int minor, int info, Collection<SonarBadge> badges) {
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
