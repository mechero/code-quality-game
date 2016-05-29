package es.macero.cqgame.modules.badges.calculators;

import es.macero.cqgame.modules.badges.domain.SonarBadge;
import es.macero.cqgame.modules.sonarapi.resultbeans.Issue;

import java.util.List;
import java.util.Optional;

public interface BadgeCalculator {

    Optional<SonarBadge> badgeFromIssueList(List<Issue> issues);

}
