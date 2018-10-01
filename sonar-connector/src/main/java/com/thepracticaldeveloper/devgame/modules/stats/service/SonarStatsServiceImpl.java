package com.thepracticaldeveloper.devgame.modules.stats.service;

import com.thepracticaldeveloper.devgame.modules.badges.domain.BadgeDetails;
import com.thepracticaldeveloper.devgame.modules.badges.domain.SonarBadge;
import com.thepracticaldeveloper.devgame.modules.stats.domain.SeverityType;
import com.thepracticaldeveloper.devgame.modules.stats.domain.SonarStatsRow;
import com.thepracticaldeveloper.devgame.modules.stats.repository.BadgeCardMongoRepository;
import com.thepracticaldeveloper.devgame.modules.stats.repository.ScoreCardMongoRepository;
import com.thepracticaldeveloper.devgame.modules.users.dao.UserMongoRepository;
import com.thepracticaldeveloper.devgame.modules.users.domain.User;
import com.thepracticaldeveloper.devgame.modules.users.dto.MessageResponseDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
final class SonarStatsServiceImpl implements SonarStatsService {

    private static final Log log = LogFactory.getLog(SonarStatsServiceImpl.class);

    private final UserMongoRepository userRepository;
    private final ScoreCardMongoRepository scoreCardMongoRepository;
    private final BadgeCardMongoRepository badgeCardMongoRepository;

    @Autowired
    public SonarStatsServiceImpl(final UserMongoRepository userRepository,
                                 final ScoreCardMongoRepository scoreCardMongoRepository,
                                 final BadgeCardMongoRepository badgeCardMongoRepository) {
        this.userRepository = userRepository;
        this.scoreCardMongoRepository = scoreCardMongoRepository;
        this.badgeCardMongoRepository = badgeCardMongoRepository;
    }

    @Override
    public List<SonarStatsRow> getSortedStatsPerUser() {
        final Stream<User> users = userRepository.findAllUsersWithTeam();
        return users.map(user -> {
            final SonarStatsRow statsRow = new SonarStatsRow(user.getAlias(), user.getTeam());
            scoreCardMongoRepository.findAllByUserId(user.getId()).forEach(scoreCard -> {
                statsRow.addTotalPoints(scoreCard.getScore());
                statsRow.addPaidDebt(scoreCard.getPaidDebtMinutes());
                addSeverityTypeCounter(statsRow, scoreCard.getSeverityType());
            });
            badgeCardMongoRepository.findAllByUserId(user.getId()).forEach(badgeCard -> {
                final BadgeDetails badgeDetails = BadgeDetails.valueOf(badgeCard.getBadgeKey());
                statsRow.addBadge(new SonarBadge(badgeDetails.getName(), badgeDetails.getDescription(), badgeCard.getScore()));
                statsRow.addTotalPoints(badgeCard.getScore());
            });
            return statsRow;
        }).sorted(Comparator.comparing(SonarStatsRow::getTotalPoints).reversed()).collect(Collectors.toList());
    }

    private void addSeverityTypeCounter(SonarStatsRow statsRow, SeverityType severityType) {
        switch (severityType) {
            case BLOCKER: {
                statsRow.addBlocker(1);
                break;
            }
            case CRITICAL: {
                statsRow.addCritical(1);
                break;
            }
            case MAJOR: {
                statsRow.addMajor(1);
                break;
            }
            case MINOR: {
                statsRow.addMinor(1);
                break;
            }
            case INFO: {
                statsRow.addInfo(1);
                break;
            }
        }
    }

    @Override
    public Collection<SonarStatsRow> getSortedStatsPerTeam() {
        return getSortedStatsPerUser().stream().collect(
                Collectors.toMap(SonarStatsRow::getUserTeam, Function.identity(), SonarStatsServiceImpl::combine))
                .values().stream()
                .sorted(Comparator.comparing(SonarStatsRow::getTotalPoints).reversed())
                .collect(Collectors.toList());
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

    @Override
    public MessageResponseDTO deleteAllStats() {
        try {
            badgeCardMongoRepository.deleteAll();
            scoreCardMongoRepository.deleteAll();
            return new MessageResponseDTO("Stats were deleted successfully");
        } catch (final Exception e) {
            log.error("Error while trying to delete statistics", e);
            return new MessageResponseDTO("Error while trying to delete statistics", true);
        }
    }
}
