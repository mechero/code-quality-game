package es.macero.cqgame.service;

import es.macero.cqgame.domain.badges.BadgeCalculator;
import es.macero.cqgame.domain.stats.SonarStats;
import es.macero.cqgame.domain.util.IssueDateFormatter;
import es.macero.cqgame.resultbeans.Issue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;

public class SonarStatsCalculatorServiceTest {

    private static final String LEGACY_DATE_STRING = "2016-05-01";
    private static final String COVERAGE_DATE_STRING = "2016-06-01";

    private SonarStatsCalculatorService service;

    @Mock
    private BadgeCalculator badgeCalculator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new SonarStatsCalculatorService(LEGACY_DATE_STRING, COVERAGE_DATE_STRING, Collections.singletonList(badgeCalculator));
        when(badgeCalculator.badgeFromIssueList(anyList())).thenReturn(Optional.empty());
    }

    @Test
    public void emptyListShouldWorkAndReturnZeroPoints() {
        final SonarStats stats = service.fromIssueList(Collections.emptyList());
        assertEquals(0, stats.getTotalPoints());
    }

    @Test
    public void issuesAfterLegacyDateAreNotProcessed() {
        final Issue issue = createIssue(LocalDate.of(2016, 5, 1), 30, SonarStats.SeverityType.BLOCKER);
        final SonarStats stats = service.fromIssueList(Collections.singletonList(issue));
        assertEquals(0, stats.getTotalPoints());
    }

    @Test
    public void issuesAtLegacyDateAreNotProcessed() {
        final Issue issue = createIssue(LocalDate.of(2016, 5, 20), 30, SonarStats.SeverityType.BLOCKER);
        final SonarStats stats = service.fromIssueList(Collections.singletonList(issue));
        assertEquals(0, stats.getTotalPoints());
    }

    @Test
    public void issuesBeforeLegacyDateAreProcessed() {
        final Issue issue = createIssue(LocalDate.of(2016, 4, 20), 30, SonarStats.SeverityType.BLOCKER);
        final SonarStats stats = service.fromIssueList(Collections.singletonList(issue));
        assertNotEquals(0, stats.getTotalPoints());
    }

    @Test
    public void issuesAfterCoverageDateAreNotProcessedForBadges() {
        final Issue issue = createIssue(LocalDate.of(2016, 7, 1), 30, SonarStats.SeverityType.BLOCKER);
        service.fromIssueList(Collections.singletonList(issue));
        // List should be empty, issue does not pass the date filter.
        verify(badgeCalculator).badgeFromIssueList(eq(Collections.emptyList()));
    }

    @Test
    public void issuesBeforeCoverageDateAreProcessedForBadges() {
        final Issue issue = createIssue(LocalDate.of(2016, 5, 20), 30, SonarStats.SeverityType.BLOCKER);
        service.fromIssueList(Collections.singletonList(issue));
        // List should be empty, issue does not pass the date filter.
        verify(badgeCalculator).badgeFromIssueList(eq(Collections.singletonList(issue)));
    }

    @Test
    public void checkStatsNumbers() {
        final LocalDate date = LocalDate.of(2016, 1, 1);
        final Issue i1 = createIssue(date, 5, SonarStats.SeverityType.MINOR);
        final Issue i2 = createIssue(date, 10, SonarStats.SeverityType.BLOCKER);
        final Issue i3 = createIssue(date, 15, SonarStats.SeverityType.CRITICAL);
        final Issue i4 = createIssue(date, 20, SonarStats.SeverityType.INFO);
        final Issue i5 = createIssue(date, 25, SonarStats.SeverityType.MAJOR);

        final SonarStats sonarStats = service.fromIssueList(Stream.of(i1, i2, i3, i4, i5).collect(Collectors.toList()));
        assertEquals(1, sonarStats.getMinor());
        assertEquals(1, sonarStats.getBlocker());
        assertEquals(1, sonarStats.getCritical());
        assertEquals(1, sonarStats.getInfo());
        assertEquals(1, sonarStats.getMajor());
        assertEquals(75, sonarStats.getTotalPaidDebt());
        assertEquals(0, sonarStats.getBadges().size());
        assertNotEquals(0, sonarStats.getTotalPoints());
    }

    private Issue createIssue(final LocalDate creationDate, final int debtMinutes, final SonarStats.SeverityType severityType) {
        final Issue issue = new Issue();
        issue.setCreationDate(IssueDateFormatter.toIssueDate(creationDate));
        issue.setDebt(debtMinutes + "m");
        issue.setSeverity(severityType.toString());
        return issue;
    }


}