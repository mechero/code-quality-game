package es.macero.cqgame.modules.badges.calculators;

import es.macero.cqgame.modules.badges.domain.SonarBadge;
import es.macero.cqgame.modules.sonarapi.resultbeans.Issue;

import java.util.Optional;
import java.util.Set;

public interface BadgeCalculator {

    Optional<SonarBadge> badgeFromIssueList(Set<Issue> issues);

}
