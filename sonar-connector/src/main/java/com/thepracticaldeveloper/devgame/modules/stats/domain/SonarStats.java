package com.thepracticaldeveloper.devgame.modules.stats.domain;

import com.thepracticaldeveloper.devgame.modules.badges.domain.SonarBadge;

import java.util.List;

public class SonarStats {

    private int totalPaidDebt;
    private int blocker;
    private int critical;
    private int major;
    private int minor;
    private int info;
    private int totalPoints;

    private List<SonarBadge> badges;

    public SonarStats(final int totalPaidDebt, final int blocker, final int critical, final int major, final int minor, final int info,
                      final int totalPoints,
                      final List<SonarBadge> badges) {
        super();
        this.totalPaidDebt = totalPaidDebt;
        this.blocker = blocker;
        this.critical = critical;
        this.major = major;
        this.minor = minor;
        this.info = info;
        this.badges = badges;
        this.totalPoints = totalPoints;
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

    public int getTotalPoints() {
        return totalPoints;
    }

    public List<SonarBadge> getBadges() {
        return badges;
    }

    @Override
    public String toString() {
        return "SonarStats [totalPaidDebt=" + totalPaidDebt + ", blocker=" + blocker + ", critical=" + critical + ", major=" + major + ", minor=" +
                minor + ", info=" + info + ", totalPoints="
                + totalPoints + ", badges=" + badges + "]";
    }

}
