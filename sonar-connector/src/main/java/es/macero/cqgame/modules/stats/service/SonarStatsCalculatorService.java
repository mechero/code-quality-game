package es.macero.cqgame.modules.stats.service;

import es.macero.cqgame.modules.sonarapi.resultbeans.Issue;
import es.macero.cqgame.modules.stats.domain.SonarStats;

import java.util.List;

public interface SonarStatsCalculatorService {
    SonarStats fromIssueList(List<Issue> issues);
}
