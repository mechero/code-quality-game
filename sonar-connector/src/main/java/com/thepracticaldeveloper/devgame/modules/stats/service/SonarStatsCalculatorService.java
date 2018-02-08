package com.thepracticaldeveloper.devgame.modules.stats.service;

import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;
import com.thepracticaldeveloper.devgame.modules.stats.domain.SonarStats;

import java.util.Set;

public interface SonarStatsCalculatorService {
    SonarStats fromIssueList(Set<Issue> issues);
}
