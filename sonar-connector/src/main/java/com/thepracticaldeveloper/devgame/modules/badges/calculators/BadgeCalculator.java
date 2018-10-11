package com.thepracticaldeveloper.devgame.modules.badges.calculators;

import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;
import com.thepracticaldeveloper.devgame.modules.stats.domain.BadgeCard;

import java.util.Optional;
import java.util.Set;

public interface BadgeCalculator {

    String badgeKey();

    Optional<BadgeCard> badgeFromIssueList(String userId, Set<Issue> issues);

}
