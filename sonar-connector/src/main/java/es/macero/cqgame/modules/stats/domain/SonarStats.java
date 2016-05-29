package es.macero.cqgame.modules.stats.domain;

import es.macero.cqgame.modules.badges.domain.SonarBadge;

import java.util.List;

public class SonarStats {
	public enum SeverityType {
		BLOCKER, CRITICAL, MAJOR, MINOR, INFO
	}

	private int totalPaidDebt;
	private int blocker;
	private int critical;
	private int major;
	private int minor;
	private int info;
	private int totalPoints;

	private List<SonarBadge> badges;

	public SonarStats(final int totalPaidDebt, final int blocker, final int critical, final int major, final int minor, final int info,
		final List<SonarBadge> badges) {
		super();
		this.totalPaidDebt = totalPaidDebt;
		this.blocker = blocker;
		this.critical = critical;
		this.major = major;
		this.minor = minor;
		this.info = info;
		this.badges = badges;
		calculatePoints();
	}

	private void calculatePoints() {
		totalPoints = totalPaidDebt + (blocker * 10) + (critical * 5) + (major * 3) + badges.stream().mapToInt(SonarBadge::getExtraPoints).sum();
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
