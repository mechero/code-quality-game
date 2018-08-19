package com.thepracticaldeveloper.devgame.modules.stats.service;

import com.thepracticaldeveloper.devgame.modules.badges.domain.SonarBadge;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;
import com.thepracticaldeveloper.devgame.modules.stats.domain.ScoreCard;
import com.thepracticaldeveloper.devgame.modules.stats.domain.SonarStats;
import com.thepracticaldeveloper.devgame.modules.stats.domain.SonarStatsRow;
import com.thepracticaldeveloper.devgame.modules.stats.repository.ScoreCardMongoRepository;
import com.thepracticaldeveloper.devgame.modules.users.dao.UserMongoRepository;
import com.thepracticaldeveloper.devgame.modules.users.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
final class SonarStatsServiceImpl implements SonarStatsService {

    private static final Log log = LogFactory.getLog(SonarStatsServiceImpl.class);

    private Map<String, User> idsAndUsers;
    private Map<String, SonarStats> statsPerId;

    private final UserMongoRepository userRepository;
    private final ScoreCardMongoRepository scoreCardMongoRepository;
    private final SonarStatsCalculatorService sonarStatsCalculatorService;

    @Autowired
    public SonarStatsServiceImpl(final UserMongoRepository userRepository,
                                 final ScoreCardMongoRepository scoreCardMongoRepository,
                                 final SonarStatsCalculatorService sonarStatsCalculatorService) {
        this.userRepository = userRepository;
        this.scoreCardMongoRepository = scoreCardMongoRepository;
        this.sonarStatsCalculatorService = sonarStatsCalculatorService;
        statsPerId = new ConcurrentHashMap<>();
        loadUsers();
    }

    private void loadUsers() {
        idsAndUsers = StreamSupport.stream(userRepository.findAllUsersWithTeam().spliterator(), false)
                .collect(Collectors.toMap(User::getLogin, Function.identity()));
    }

    @Override
    public Set<String> getIds() {
        return idsAndUsers.keySet();
    }

    @Override
    public void updateStats(final String sonarLogin, final Set<Issue> issues) {
        SonarStats stats = sonarStatsCalculatorService.fromIssueList(issues);
        statsPerId.put(sonarLogin, stats);
        log.info("Processing " + sonarLogin + "; Stats: " + stats);
    }

    @Override
    public Collection<User> getUsers() {
        return idsAndUsers.values();
    }

    @Override
    public Collection<SonarStatsRow> getSortedStatsPerUser() {
        List<SonarStatsRow> rows = new ArrayList<>();
        for (Entry<String, SonarStats> entry : statsPerId.entrySet()) {
            User user = idsAndUsers.get(entry.getKey());
            SonarStats stats = entry.getValue();
            rows.add(new SonarStatsRow(user.getAlias(), user.getTeam(), stats.getTotalPoints(),
                    stats.getTotalPaidDebt(), stats.getBlocker(),
                    stats.getCritical(), stats.getMajor(), stats.getMinor(),
                    stats.getInfo(), stats.getBadges()));
        }
        return rows.stream().sorted((r1, r2) -> Integer.compare(r2.getTotalPoints(), r1.getTotalPoints())).collect(Collectors.toList());
    }

    @Override
    public Collection<SonarStatsRow> getSortedStatsPerTeam() {
        return getSortedStatsPerUser().stream().collect(
                Collectors.toMap(SonarStatsRow::getUserTeam, Function.identity(), SonarStatsServiceImpl::combine))
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
