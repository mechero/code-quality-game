package com.thepracticaldeveloper.devgame.modules.stats.service;

import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;
import com.thepracticaldeveloper.devgame.modules.stats.domain.SonarStatsRow;
import com.thepracticaldeveloper.devgame.modules.users.domain.User;

import java.util.Collection;
import java.util.Set;

public interface SonarStatsService {
    Set<String> getIds();

    void updateStats(String id, Set<Issue> issues);

    Collection<User> getUsers();

    Collection<SonarStatsRow> getSortedStatsPerUser();

    Collection<SonarStatsRow> getSortedStatsPerTeam();
}
