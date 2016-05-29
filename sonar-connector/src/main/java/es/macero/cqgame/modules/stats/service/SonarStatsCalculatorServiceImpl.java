package es.macero.cqgame.modules.stats.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import es.macero.cqgame.modules.badges.calculators.BadgeCalculator;
import es.macero.cqgame.modules.badges.domain.SonarBadge;
import es.macero.cqgame.modules.stats.domain.SonarStats;
import es.macero.cqgame.util.IssueDateFormatter;
import es.macero.cqgame.modules.sonarapi.resultbeans.Issue;
import es.macero.cqgame.util.Utils;

@Component
final class SonarStatsCalculatorServiceImpl implements SonarStatsCalculatorService {

	private static final Log log = LogFactory.getLog(SonarStatsCalculatorServiceImpl.class);

	private List<BadgeCalculator> badgeCalculators;

	private LocalDate legacyDate;
	private LocalDate coverageDate;

	@Autowired
	SonarStatsCalculatorServiceImpl(@Value("${legacyDate}") final String legacyDateString, @Value("${coverageDate}") final String coverageDateString,
									final List<BadgeCalculator> badgeCalculators) {
		this.legacyDate = LocalDate.parse(legacyDateString);
		this.coverageDate = LocalDate.parse(coverageDateString);
		this.badgeCalculators = badgeCalculators;
	}

	@Override
	public SonarStats fromIssueList(final List<Issue> issues) {
		// For the stats we only use those issues created before 'legacy date'
		issues.stream().map(i -> IssueDateFormatter.format(i.getCreationDate())).forEach(log::info);
		List<Issue> issuesFilteredByLegacyDate = issues.stream()
			.filter(i -> IssueDateFormatter.format(i.getCreationDate())
				.isBefore(legacyDate)).collect(Collectors.toList());
		List<Issue> issuesFilteredByCovDate = issues.stream()
			.filter(i -> IssueDateFormatter.format(i.getCreationDate())
				.isBefore(coverageDate)).collect(Collectors.toList());

		int debtSum = (int) issuesFilteredByLegacyDate.stream().map(Issue::getDebt)
			.filter(c -> c != null).map(Utils::durationTranslator)
			.map(Duration::parse).mapToLong(Duration::toMinutes)
			.sum();

		Map<String, Long> typeCount = issuesFilteredByLegacyDate.stream()
			.collect(Collectors.groupingBy(Issue::getSeverity, Collectors.counting()));
		int blocker = getTotalIssuesForType(SonarStats.SeverityType.BLOCKER, typeCount);
		int critical = getTotalIssuesForType(SonarStats.SeverityType.CRITICAL, typeCount);
		int major = getTotalIssuesForType(SonarStats.SeverityType.MAJOR, typeCount);
		int minor = getTotalIssuesForType(SonarStats.SeverityType.MINOR, typeCount);
		int info = getTotalIssuesForType(SonarStats.SeverityType.INFO, typeCount);

		// Badge calculators use all the issues resolved no matter what date they were created
		List<SonarBadge> badges = badgeCalculators.stream().map(c -> c.badgeFromIssueList(issuesFilteredByCovDate))
			.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		return new SonarStats(debtSum, blocker, critical, major, minor, info, badges);
	}

	private static int getTotalIssuesForType(final SonarStats.SeverityType type, final Map<String, Long> typeCount) {
		return typeCount.getOrDefault(type.toString(), 0L).intValue();
	}

}
