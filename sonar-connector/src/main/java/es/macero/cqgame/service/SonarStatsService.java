package es.macero.cqgame.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.macero.cqgame.dao.SonarUserRepository;
import es.macero.cqgame.domain.badges.BadgeCalculator;
import es.macero.cqgame.domain.badges.SonarBadge;
import es.macero.cqgame.domain.stats.SonarStats;
import es.macero.cqgame.domain.stats.SonarStatsRow;
import es.macero.cqgame.domain.users.SonarUser;
import es.macero.cqgame.domain.util.IssueDateFormatter;
import es.macero.cqgame.resultbeans.Issue;
import es.macero.cqgame.util.Utils;

@Service
public class SonarStatsService
{
    public static final LocalDate LEGACY_LOCALDATETIME = LocalDate.of(2015, 2, 9);
    public static final LocalDate COVERAGE_LOCALDATETIME = LocalDate.of(2015, 5, 29);
    
    private Map<String, SonarUser> idsAndUsers;

    private Map<String, SonarStats> statsPerId;
    
    @Autowired
    private SonarUserRepository sonarDao;
    
    @Autowired
    private List<BadgeCalculator> badgeCalculators;

    @PostConstruct
    public void init()
    {
        idsAndUsers = sonarDao.findAll().stream().collect(Collectors.toMap(SonarUser::getId, Function.identity()));
        statsPerId = new ConcurrentHashMap<>();
    }

    public Set<String> getIds()
    {
        return idsAndUsers.keySet();
    }

    public Collection<SonarUser> getUsers()
    {
        return idsAndUsers.values();
    }

    public void updateStats(String id, List<Issue> issues)
    {
        SonarStats stats = fromIssueList(issues);
        statsPerId.put(id, stats);
        System.out.println("Processing " + id + "; Stats: " + stats);
    }

    public Collection<SonarStatsRow> getSortedStatsPerUser()
    {
        List<SonarStatsRow> rows = new ArrayList<>();
        for (Entry<String, SonarStats> entry : statsPerId.entrySet())
        {
            SonarUser user = idsAndUsers.get(entry.getKey());
            SonarStats stats = entry.getValue();
            rows.add(new SonarStatsRow(user.getAlias(), user.getTeam(), stats.getTotalPoints(), stats.getTotalPaidDebt(), stats.getBlocker(), stats.getCritical(), stats.getMajor(), stats.getMinor(),
                    stats.getInfo(), stats.getBadges()));
        }
        return rows.stream().sorted((r1, r2) -> Integer.compare(r2.getTotalPoints(), r1.getTotalPoints())).collect(Collectors.toList());
    }

    public Collection<SonarStatsRow> getSortedStatsPerTeam()
    {
        return getSortedStatsPerUser().stream().collect(Collectors.toMap(SonarStatsRow::getUserTeam, Function.identity(), (r1, r2) -> combine(r1, r2))).values().stream()
                .sorted((r1, r2) -> Integer.compare(r2.getTotalPoints(), r1.getTotalPoints())).collect(Collectors.toList());
    }

    private SonarStatsRow combine(SonarStatsRow r1, SonarStatsRow r2)
    {
        Set<SonarBadge> allBadges = new HashSet<SonarBadge>();
        allBadges.addAll(r1.getBadges());
        allBadges.addAll(r2.getBadges());
        return new SonarStatsRow(r1.getUserAlias(), r1.getUserTeam(), r1.getTotalPoints() + r2.getTotalPoints(), r1.getTotalPaidDebt() + r2.getTotalPaidDebt(), r1.getBlocker() + r2.getBlocker(),
                r1.getCritical() + r2.getCritical(), r1.getMajor() + r2.getMajor(), r1.getMinor() + r2.getMinor(), r1.getInfo() + r2.getInfo(), allBadges);
    }
    
    private SonarStats fromIssueList(List<Issue> issues)
    {
        // For the stats we only use those issues created before 'legacy date'
        List<Issue> issuesFilteredByLegacyDate = issues.stream().filter(i -> IssueDateFormatter.format(i.getCreationDate()).isBefore(LEGACY_LOCALDATETIME)).collect(Collectors.toList());
        List<Issue> issuesFilteredByCovDate = issues.stream().filter(i -> IssueDateFormatter.format(i.getCreationDate()).isBefore(COVERAGE_LOCALDATETIME)).collect(Collectors.toList());
        
        int debtSum = (int) issuesFilteredByLegacyDate.stream().map(c -> c.getDebt()).filter(c -> c != null).map(c -> Utils.durationTranslator(c)).map(c -> Duration.parse(c)).mapToLong(Duration::toMinutes).sum();
        
        Map<String, Long> typeCount = issuesFilteredByLegacyDate.stream().collect(Collectors.groupingBy(Issue::getSeverity, Collectors.counting()));
        int blocker = getTotalIssuesForType(SonarStats.SeverityType.BLOCKER, typeCount);
        int critical = getTotalIssuesForType(SonarStats.SeverityType.CRITICAL, typeCount);
        int major = getTotalIssuesForType(SonarStats.SeverityType.MAJOR, typeCount);
        int minor = getTotalIssuesForType(SonarStats.SeverityType.MINOR, typeCount);
        int info = getTotalIssuesForType(SonarStats.SeverityType.INFO, typeCount);
        
        // Badge calculators use all the issues resolved no matter what date they were created
        List<SonarBadge> badges = badgeCalculators.stream().map(c -> c.badgeFromIssueList(issuesFilteredByCovDate)).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        
        return new SonarStats(debtSum, blocker, critical, major, minor, info, badges);
    }

    private static int getTotalIssuesForType(SonarStats.SeverityType type, Map<String, Long> typeCount)
    {
        return typeCount.getOrDefault(type.toString(), 0L).intValue();
    }


}
