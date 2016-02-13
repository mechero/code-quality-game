package es.macero.cqgame.service;

import es.macero.cqgame.dao.SonarUserRepository;
import es.macero.cqgame.domain.badges.BadgeCalculator;
import es.macero.cqgame.domain.badges.SonarBadge;
import es.macero.cqgame.domain.stats.SonarStats;
import es.macero.cqgame.domain.stats.SonarStatsRow;
import es.macero.cqgame.domain.stats.SonarStatsRowBuilder;
import es.macero.cqgame.domain.users.SonarUser;
import es.macero.cqgame.domain.util.IssueDateFormatter;
import es.macero.cqgame.resultbeans.Issue;
import es.macero.cqgame.util.Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SonarStatsService {

    private static final Log log = LogFactory.getLog(SonarStatsService.class);

    @Value("${legacyDate}")
    private String legacyDateString;

    @Value("${coverageDate}")
    private String coverageDateString;

    private Map<String, SonarUser> idsAndUsers;
    private Map<String, SonarStats> statsPerId;
    private SonarUserRepository sonarDao;
    private List<BadgeCalculator> badgeCalculators = new ArrayList<>();

    private LocalDate legacyDate;
    private LocalDate coverageDate;

    @Autowired
    public void setSonarDao(SonarUserRepository sonarDao) {
        this.sonarDao = sonarDao;
    }

    @Autowired(required = false)
    public void setBadgeCalculators(List<BadgeCalculator> badgeCalculators) {
        this.badgeCalculators = badgeCalculators;
    }

    @PostConstruct
    public void init() {
        idsAndUsers = sonarDao.findAll().stream().collect(Collectors.toMap(SonarUser::getId, Function.identity()));
        statsPerId = new ConcurrentHashMap<>();
        legacyDate = LocalDate.parse(legacyDateString);
        coverageDate = LocalDate.parse(coverageDateString);
        log.info("Legacy date is " + legacyDate);
        log.info("Coverage date is " + coverageDate);
    }

    public Set<String> getIds() {
        return idsAndUsers.keySet();
    }

    public Collection<SonarUser> getUsers() {
        return idsAndUsers.values();
    }

    public void updateStats(String id, List<Issue> issues) {
        SonarStats stats = fromIssueList(issues);
        statsPerId.put(id, stats);
        log.info("Processing " + id + "; Stats: " + stats);
    }

    public Collection<SonarStatsRow> getSortedStatsPerUser() {
        List<SonarStatsRow> rows = new ArrayList<>();
        for (Entry<String, SonarStats> entry : statsPerId.entrySet()) {
            SonarUser user = idsAndUsers.get(entry.getKey());
            SonarStats stats = entry.getValue();
            rows.add(new SonarStatsRowBuilder(user.getAlias(), user.getTeam())
                    .withTotalPoints(stats.getTotalPoints())
                    .withTotalPaidDebt(stats.getTotalPaidDebt())
                    .withBlocker(stats.getBlocker())
                    .withCritical(stats.getCritical())
                    .withMajor(stats.getMajor())
                    .withMinor(stats.getMinor())
                    .withInfo(stats.getInfo())
                    .withBadges(stats.getBadges())
                    .createSonarStatsRow());
        }
        return rows.stream().sorted((r1, r2) -> Integer.compare(r2.getTotalPoints(), r1.getTotalPoints())).collect(Collectors.toList());
    }

    public Collection<SonarStatsRow> getSortedStatsPerTeam() {
        return getSortedStatsPerUser().stream().collect(
                Collectors.toMap(SonarStatsRow::getUserTeam, Function.identity(), SonarStatsService::combine))
                .values().stream()
                .sorted((r1, r2) -> Integer.compare(r2.getTotalPoints(), r1.getTotalPoints())).collect(Collectors.toList());
    }

    private static SonarStatsRow combine(SonarStatsRow r1, SonarStatsRow r2) {
        Set<SonarBadge> allBadges = new HashSet<>();
        allBadges.addAll(r1.getBadges());
        allBadges.addAll(r2.getBadges());
        return new SonarStatsRowBuilder(r1.getUserAlias(), r1.getUserTeam())
                .withTotalPoints(r1.getTotalPoints() + r2.getTotalPoints())
                .withTotalPaidDebt(r1.getTotalPaidDebt() + r2.getTotalPaidDebt())
                .withBlocker(r1.getBlocker() + r2.getBlocker())
                .withCritical(r1.getCritical() + r2.getCritical())
                .withMajor(r1.getMajor() + r2.getMajor())
                .withMinor(r1.getMinor() + r2.getMinor())
                .withInfo(r1.getInfo() + r2.getInfo())
                .withBadges(allBadges).createSonarStatsRow();
    }

    private SonarStats fromIssueList(List<Issue> issues) {
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

    private static int getTotalIssuesForType(SonarStats.SeverityType type, Map<String, Long> typeCount) {
        return typeCount.getOrDefault(type.toString(), 0L).intValue();
    }


}
