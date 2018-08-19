package com.thepracticaldeveloper.devgame.modules.stats.service;

import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;

import java.util.Set;

public interface BadgeService {
    void saveNewBadgesFromIssueList(String userId, Set<Issue> issues);
}
