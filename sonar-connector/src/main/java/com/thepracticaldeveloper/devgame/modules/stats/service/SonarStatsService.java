package com.thepracticaldeveloper.devgame.modules.stats.service;

import com.thepracticaldeveloper.devgame.modules.stats.domain.SonarStatsRow;
import com.thepracticaldeveloper.devgame.modules.users.dto.MessageResponseDTO;

import java.util.Collection;

public interface SonarStatsService {
    Collection<SonarStatsRow> getSortedStatsPerUser();

    Collection<SonarStatsRow> getSortedStatsPerTeam();

    MessageResponseDTO deleteAllStats();
}
