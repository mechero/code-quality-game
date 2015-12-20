package es.macero.cqgame.domain.badges;

import es.macero.cqgame.resultbeans.Issue;

import java.util.List;
import java.util.Optional;

public interface BadgeCalculator {

    Optional<SonarBadge> badgeFromIssueList(List<Issue> issues);

}
