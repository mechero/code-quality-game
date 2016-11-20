package es.macero.cqgame.modules.stats.service;

import es.macero.cqgame.modules.sonarapi.resultbeans.Issue;
import es.macero.cqgame.modules.stats.domain.SonarStatsRow;
import es.macero.cqgame.modules.users.domain.SonarUser;

import java.util.Collection;
import java.util.Set;

public interface SonarStatsService {
    Set<String> getIds();

    void updateStats(String id, Set<Issue> issues);

    Collection<SonarUser> getUsers();

    Collection<SonarStatsRow> getSortedStatsPerUser();

    Collection<SonarStatsRow> getSortedStatsPerTeam();
}
