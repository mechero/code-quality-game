package com.thepracticaldeveloper.devgame.modules.badges.calculators;

import com.thepracticaldeveloper.devgame.modules.badges.domain.BadgeDetails;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;
import com.thepracticaldeveloper.devgame.modules.stats.domain.BadgeCard;
import com.thepracticaldeveloper.devgame.util.IssueDateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class EarlyBirdBadgeCalculator implements BadgeCalculator {

    private static final Logger log = LoggerFactory.getLogger(EarlyBirdBadgeCalculator.class);

    public static final String KEY = BadgeDetails.EARLY_BIRD.name();

    private static final int EXTRA_POINTS = 100;

    private final LocalDate earlyBirdDate;

    public EarlyBirdBadgeCalculator(@Value("${game.dates.earlyBird}") final String earlyBirdDate) {
        this.earlyBirdDate = LocalDate.parse(earlyBirdDate);
        log.info("Early Bird Badge is configured with max date {}", this.earlyBirdDate);
    }

    @Override
    public String badgeKey() {
        return KEY;
    }

    @Override
    public Optional<BadgeCard> badgeFromIssueList(final String userId, Set<Issue> issues) {
        if (issues.stream().filter(i -> i.getCloseDate() != null)
                .anyMatch(i -> IssueDateFormatter.format(i.getCloseDate()).isBefore(earlyBirdDate))) {
            return Optional.of(new BadgeCard(UUID.randomUUID().toString(), userId, KEY, Instant.now(), EXTRA_POINTS));
        } else {
            return Optional.empty();
        }
    }

}
