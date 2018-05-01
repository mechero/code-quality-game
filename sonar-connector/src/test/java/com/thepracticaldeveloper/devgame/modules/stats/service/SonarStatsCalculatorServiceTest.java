package com.thepracticaldeveloper.devgame.modules.stats.service;

import com.thepracticaldeveloper.devgame.modules.badges.calculators.BadgeCalculator;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;
import com.thepracticaldeveloper.devgame.modules.stats.domain.SonarStats;
import com.thepracticaldeveloper.devgame.util.IssueDateFormatter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SonarStatsCalculatorServiceTest {

    private static final String LEGACY_DATE_STRING = "2016-05-01";

    private SonarStatsCalculatorService service;

    @Mock
    private BadgeCalculator badgeCalculator;

    @Before
    public void setUp() {
        service = new SonarStatsCalculatorServiceImpl(LEGACY_DATE_STRING, Collections.singletonList(badgeCalculator));
        given(badgeCalculator.badgeFromIssueList(anySet())).willReturn(Optional.empty());
    }

    @Test
    public void emptyListShouldWorkAndReturnZeroPoints() {
        final SonarStats stats = service.fromIssueList(Collections.emptySet());
        assertThat(stats.getTotalPoints()).isZero();
    }

    @Test
    public void issuesAfterLegacyDateAreNotProcessed() {
        final Issue issue = createIssue(LocalDate.of(2016, 5, 1), 30, SonarStats.SeverityType.BLOCKER);
        final SonarStats stats = service.fromIssueList(Collections.singleton(issue));
        assertThat(stats.getTotalPoints()).isZero();
    }

    @Test
    public void issuesAtLegacyDateAreNotProcessed() {
        final Issue issue = createIssue(LocalDate.of(2016, 5, 20), 30, SonarStats.SeverityType.BLOCKER);
        final SonarStats stats = service.fromIssueList(Collections.singleton(issue));
        assertThat(stats.getTotalPoints()).isZero();
    }

    @Test
    public void issuesBeforeLegacyDateAreProcessed() {
        final Issue issue = createIssue(LocalDate.of(2016, 4, 20), 30, SonarStats.SeverityType.BLOCKER);
        final SonarStats stats = service.fromIssueList(Collections.singleton(issue));
        assertThat(stats.getTotalPoints()).isNotZero();
    }

    @Test
    public void issuesBeforeCoverageDateAreProcessedForBadges() {
        final Issue issue = createIssue(LocalDate.of(2016, 5, 20), 30, SonarStats.SeverityType.BLOCKER);
        service.fromIssueList(Collections.singleton(issue));
        // List should be empty, issue does not pass the date filter.
        verify(badgeCalculator).badgeFromIssueList(eq(Collections.singleton(issue)));
    }

    @Test
    public void checkStatsNumbers() {
        final LocalDate date = LocalDate.of(2016, 1, 1);
        final Issue i1 = createIssue(date, 5, SonarStats.SeverityType.MINOR);
        final Issue i2 = createIssue(date, 10, SonarStats.SeverityType.BLOCKER);
        final Issue i3 = createIssue(date, 15, SonarStats.SeverityType.CRITICAL);
        final Issue i4 = createIssue(date, 20, SonarStats.SeverityType.INFO);
        final Issue i5 = createIssue(date, 25, SonarStats.SeverityType.MAJOR);

        final SonarStats sonarStats = service.fromIssueList(Stream.of(i1, i2, i3, i4, i5).collect(Collectors.toSet()));
        assertThat(sonarStats.getMinor()).isEqualTo(1);
        assertThat(sonarStats.getBlocker()).isEqualTo(1);
        assertThat(sonarStats.getCritical()).isEqualTo(1);
        assertThat(sonarStats.getInfo()).isEqualTo(1);
        assertThat(sonarStats.getMajor()).isEqualTo(1);
        assertThat(sonarStats.getTotalPaidDebt()).isEqualTo(75);
        assertThat(sonarStats.getBadges()).isEmpty();
        assertThat(sonarStats.getTotalPoints()).isNotZero();
    }

    private Issue createIssue(final LocalDate creationDate, final int debtMinutes, final SonarStats.SeverityType severityType) {
        final Issue issue = new Issue();
        issue.setKey(RandomStringUtils.random(15));
        issue.setComponent(RandomStringUtils.random(10));
        issue.setResolution("FIXED");
        issue.setCreationDate(IssueDateFormatter.toIssueDate(creationDate));
        issue.setDebt(debtMinutes + "m");
        issue.setSeverity(severityType.toString());
        return issue;
    }


}