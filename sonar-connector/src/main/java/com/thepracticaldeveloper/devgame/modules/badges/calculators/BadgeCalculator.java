package com.thepracticaldeveloper.devgame.modules.badges.calculators;

import com.thepracticaldeveloper.devgame.modules.badges.domain.SonarBadge;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;

import java.util.Optional;
import java.util.Set;

public interface BadgeCalculator {

    Optional<SonarBadge> badgeFromIssueList(Set<Issue> issues);

}
