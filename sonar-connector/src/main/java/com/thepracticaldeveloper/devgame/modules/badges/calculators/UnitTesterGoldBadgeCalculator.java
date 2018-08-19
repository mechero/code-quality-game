package com.thepracticaldeveloper.devgame.modules.badges.calculators;

import com.thepracticaldeveloper.devgame.modules.badges.domain.BadgeDetails;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;
import com.thepracticaldeveloper.devgame.modules.stats.domain.BadgeCard;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class UnitTesterGoldBadgeCalculator implements BadgeCalculator {

    public static final String KEY = BadgeDetails.UNIT_TESTER_GOLD.name();

    private static final int EXTRA_POINTS = 1000;

    private static final String RULE_ID_LINE = "InsufficientLineCoverage";
    private static final String RULE_ID_BRANCH = "InsufficientBranchCoverage";

    @Override
    public String badgeKey() {
        return "UT_GOLD";
    }

    @Override
    public Optional<BadgeCard> badgeFromIssueList(final String userId, final Set<Issue> issues) {
        long count = issues.stream()
                .filter(i -> i.getRule().contains(RULE_ID_LINE) || i.getRule().contains(RULE_ID_BRANCH)).count();
        if (count >= 100) {
            return Optional.of(new BadgeCard(UUID.randomUUID().toString(), userId, KEY, Instant.now(), EXTRA_POINTS));
        } else {
            return Optional.empty();
        }
    }

}
