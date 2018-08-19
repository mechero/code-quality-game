package com.thepracticaldeveloper.devgame.modules.stats.service;

import com.thepracticaldeveloper.devgame.modules.badges.calculators.BadgeCalculator;
import com.thepracticaldeveloper.devgame.modules.badges.domain.SonarBadge;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;
import com.thepracticaldeveloper.devgame.modules.stats.domain.ScoreCard;
import com.thepracticaldeveloper.devgame.modules.stats.domain.SeverityType;
import com.thepracticaldeveloper.devgame.modules.stats.domain.SonarStats;
import com.thepracticaldeveloper.devgame.util.IssueDateFormatter;
import com.thepracticaldeveloper.devgame.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
final class SonarStatsCalculatorServiceImpl implements SonarStatsCalculatorService {

    private static final Logger log = LoggerFactory.getLogger(SonarStatsCalculatorServiceImpl.class);

    public static final String FIXED = "FIXED";

    private List<BadgeCalculator> badgeCalculators;

    private LocalDate legacyDate;

    @Autowired
    SonarStatsCalculatorServiceImpl(@Value("${game.dates.legacy}") final String legacyDateString,
                                    final List<BadgeCalculator> badgeCalculators) {
        this.legacyDate = LocalDate.parse(legacyDateString);
        this.badgeCalculators = badgeCalculators;
    }

    @Override
    public SonarStats fromIssueList(final Set<Issue> issues) {

        Set<Issue> fixedIssues = issues.stream().filter(
                i -> i.getResolution() != null && i.getResolution().equals(FIXED)).
                collect(Collectors.toSet());
        log.trace("Fixed " + fixedIssues.size() + " issues of " + issues.size() + " total number of issues assigned");

        // For the stats we only use those issues created before 'legacy date'
        Set<Issue> issuesFilteredByLegacyDate = fixedIssues.stream()
                .filter(i -> IssueDateFormatter.format(i.getCreationDate())
                        .isBefore(legacyDate)).collect(Collectors.toSet());
        log.trace("Legacy Issues " + issuesFilteredByLegacyDate.size());

        int debtSum = (int) issuesFilteredByLegacyDate.stream().map(Issue::getDebt)
                .filter(c -> c != null).map(Utils::durationTranslator)
                .map(Duration::parse).mapToLong(Duration::toMinutes)
                .sum();

        Map<String, Long> typeCount = issuesFilteredByLegacyDate.stream()
                .collect(Collectors.groupingBy(Issue::getSeverity, Collectors.counting()));
        int blocker = getTotalIssuesForType(SeverityType.BLOCKER, typeCount);
        int critical = getTotalIssuesForType(SeverityType.CRITICAL, typeCount);
        int major = getTotalIssuesForType(SeverityType.MAJOR, typeCount);
        int minor = getTotalIssuesForType(SeverityType.MINOR, typeCount);
        int info = getTotalIssuesForType(SeverityType.INFO, typeCount);

        // Badge calculators
        List<SonarBadge> badges = badgeCalculators.stream().map(c -> c.badgeFromIssueList(fixedIssues))
                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        return new SonarStats(debtSum, blocker, critical, major, minor, info, badges);
    }

    private static int getTotalIssuesForType(final SeverityType type, final Map<String, Long> typeCount) {
        return typeCount.getOrDefault(type.toString(), 0L).intValue();
    }

}
