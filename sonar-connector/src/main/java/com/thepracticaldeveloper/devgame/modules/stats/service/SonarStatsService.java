package com.thepracticaldeveloper.devgame.modules.stats.service;

import com.thepracticaldeveloper.devgame.modules.stats.domain.SonarStatsRow;

import java.util.Collection;

public interface SonarStatsService {
    Collection<SonarStatsRow> getSortedStatsPerUser();

    Collection<SonarStatsRow> getSortedStatsPerTeam();
}
