package es.macero.cqgame.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.macero.cqgame.dao.SonarUserRepository;
import es.macero.cqgame.domain.badges.SonarBadge;
import es.macero.cqgame.domain.stats.SonarStats;
import es.macero.cqgame.domain.stats.SonarStatsRow;
import es.macero.cqgame.domain.users.SonarUser;
import es.macero.cqgame.resultbeans.Issue;

@Service
public class SonarStatsService {

    private static final Log log = LogFactory.getLog(SonarStatsService.class);

    private Map<String, SonarUser> idsAndUsers;
    private Map<String, SonarStats> statsPerId;

    private final SonarUserRepository sonarDao;
    private final SonarStatsCalculatorService sonarStatsCalculatorService;

    @Autowired
    public SonarStatsService(final SonarUserRepository sonarDao, final SonarStatsCalculatorService sonarStatsCalculatorService) {
        this.sonarDao = sonarDao;
        this.sonarStatsCalculatorService = sonarStatsCalculatorService;
        statsPerId = new ConcurrentHashMap<>();
        loadUsers();
    }

    private void loadUsers() {
        idsAndUsers = sonarDao.findAll().stream().collect(Collectors.toMap(SonarUser::getId, Function.identity()));
    }

    Set<String> getIds() {
        return idsAndUsers.keySet();
    }

    void updateStats(final String id, final List<Issue> issues) {
        SonarStats stats = sonarStatsCalculatorService.fromIssueList(issues);
        statsPerId.put(id, stats);
        log.info("Processing " + id + "; Stats: " + stats);
    }

    public Collection<SonarUser> getUsers() {
        return idsAndUsers.values();
    }

    public Collection<SonarStatsRow> getSortedStatsPerUser() {
        List<SonarStatsRow> rows = new ArrayList<>();
        for (Entry<String, SonarStats> entry : statsPerId.entrySet()) {
            SonarUser user = idsAndUsers.get(entry.getKey());
            SonarStats stats = entry.getValue();
            rows.add(new SonarStatsRow(user.getAlias(), user.getTeam(), stats.getTotalPoints(),
                    stats.getTotalPaidDebt(), stats.getBlocker(),
                    stats.getCritical(), stats.getMajor(), stats.getMinor(),
                    stats.getInfo(), stats.getBadges()));
        }
        return rows.stream().sorted((r1, r2) -> Integer.compare(r2.getTotalPoints(), r1.getTotalPoints())).collect(Collectors.toList());
    }

    public Collection<SonarStatsRow> getSortedStatsPerTeam() {
        return getSortedStatsPerUser().stream().collect(
                Collectors.toMap(SonarStatsRow::getUserTeam, Function.identity(), SonarStatsService::combine))
                .values().stream()
                .sorted((r1, r2) -> Integer.compare(r2.getTotalPoints(), r1.getTotalPoints())).collect(Collectors.toList());
    }

    private static SonarStatsRow combine(final SonarStatsRow r1, final SonarStatsRow r2) {
        Set<SonarBadge> allBadges = new HashSet<>();
        allBadges.addAll(r1.getBadges());
        allBadges.addAll(r2.getBadges());
        return new SonarStatsRow(r1.getUserAlias(), r1.getUserTeam(), r1.getTotalPoints() + r2.getTotalPoints(),
                r1.getTotalPaidDebt() + r2.getTotalPaidDebt(), r1.getBlocker() + r2.getBlocker(),
                r1.getCritical() + r2.getCritical(), r1.getMajor() + r2.getMajor(),
                r1.getMinor() + r2.getMinor(), r1.getInfo() + r2.getInfo(), allBadges);
    }


}
