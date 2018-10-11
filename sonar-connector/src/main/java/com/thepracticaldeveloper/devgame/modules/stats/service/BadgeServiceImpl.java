package com.thepracticaldeveloper.devgame.modules.stats.service;

import com.thepracticaldeveloper.devgame.modules.badges.calculators.BadgeCalculator;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;
import com.thepracticaldeveloper.devgame.modules.stats.domain.BadgeCard;
import com.thepracticaldeveloper.devgame.modules.stats.repository.BadgeCardMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BadgeServiceImpl implements BadgeService {

    private final List<BadgeCalculator> badgeCalculators;
    private final BadgeCardMongoRepository badgeRepository;

    public BadgeServiceImpl(final List<BadgeCalculator> badgeCalculators,
                            final BadgeCardMongoRepository badgeRepository) {
        this.badgeCalculators = badgeCalculators;
        this.badgeRepository = badgeRepository;
    }

    @Override
    public void saveNewBadgesFromIssueList(final String userId, final Set<Issue> issues) {
        final Set<String> existingBadgeKeys = badgeRepository.findAllByUserId(userId)
                .map(BadgeCard::getBadgeKey).collect(Collectors.toSet());
        // Filter out those badges already won
        final List<BadgeCalculator> applicableCalculators = badgeCalculators.stream()
                .filter(c -> !existingBadgeKeys.contains(c.badgeKey())).collect(Collectors.toList());
        for (var badgeCalculator : applicableCalculators) {
            badgeCalculator.badgeFromIssueList(userId, issues).map(badgeRepository::save);
        }
    }
}
