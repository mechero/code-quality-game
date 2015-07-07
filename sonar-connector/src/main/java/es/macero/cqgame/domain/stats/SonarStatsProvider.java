package es.macero.cqgame.domain.stats;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import es.macero.cqgame.domain.badges.BadgeCalculator;
import es.macero.cqgame.domain.badges.SonarBadge;
import es.macero.cqgame.resultbeans.Issue;

public final class SonarStatsProvider
{
    @Autowired
    private List<BadgeCalculator> badgeCalculators;
    
    public SonarStats fromIssueList(List<Issue> issues)
    {
        int debtSum = (int) issues.stream().map(c -> c.getDebt()).filter(c -> c != null).map(c -> durationTranslator(c)).map(c -> Duration.parse(c)).mapToLong(Duration::toMinutes).sum();
        Map<String, Long> typeCount = issues.stream().collect(Collectors.groupingBy(Issue::getSeverity, Collectors.counting()));
        int blocker = getTotalIssuesForType(SonarStats.SeverityType.BLOCKER, typeCount);
        int critical = getTotalIssuesForType(SonarStats.SeverityType.CRITICAL, typeCount);
        int major = getTotalIssuesForType(SonarStats.SeverityType.MAJOR, typeCount);
        int minor = getTotalIssuesForType(SonarStats.SeverityType.MINOR, typeCount);
        int info = getTotalIssuesForType(SonarStats.SeverityType.INFO, typeCount);
        List<SonarBadge> badges = badgeCalculators.stream().map(c -> c.badgeFromIssueList(issues)).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        return new SonarStats(debtSum, blocker, critical, major, minor, info, badges);
    }

    private static int getTotalIssuesForType(SonarStats.SeverityType type, Map<String, Long> typeCount)
    {
        return typeCount.getOrDefault(type.toString(), 0L).intValue();
    }

    public static String durationTranslator(String sonarDuration)
    {
        return "P" + (sonarDuration.contains("d") ? sonarDuration.replaceAll("d", "D") : "") + "T" + sonarDuration.replaceAll("min", "M").replaceAll("h", "H");
    }
}
