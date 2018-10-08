package com.thepracticaldeveloper.devgame.modules.stats.service;

import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;
import com.thepracticaldeveloper.devgame.modules.stats.domain.ScoreCard;
import com.thepracticaldeveloper.devgame.modules.stats.domain.SeverityType;
import com.thepracticaldeveloper.devgame.modules.stats.repository.ScoreCardMongoRepository;
import com.thepracticaldeveloper.devgame.util.IssueDateFormatter;
import com.thepracticaldeveloper.devgame.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ScoreCardServiceImpl implements ScoreCardService {

    private static final String FIXED = "FIXED";
    private static final Logger log = LoggerFactory.getLogger(ScoreCardServiceImpl.class);

    private final LocalDate legacyDate;
    private final ScoreCardMongoRepository cardRepository;

    public ScoreCardServiceImpl(@Value("${game.dates.legacy}") final String legacyDate,
                                final ScoreCardMongoRepository cardRepository) {
        this.legacyDate = LocalDate.parse(legacyDate);
        this.cardRepository = cardRepository;
        log.info("Legacy date is configured to {}", legacyDate);
    }

    @Override
    public void saveNewCardsFromIssueList(final String userId, final Set<Issue> issues) {
        final Set<Issue> fixedIssues = issues.stream().filter(
                i -> i.getResolution() != null && i.getResolution().equals(FIXED)).
                collect(Collectors.toSet());
        log.trace("Fixed {} issues of {} issues assigned", fixedIssues.size(), issues.size());

        // For the stats we only use those issues created before 'legacy date'
        final Set<Issue> issuesFilteredByLegacyDate = fixedIssues.stream()
                .filter(i -> IssueDateFormatter.format(i.getCreationDate())
                        .isBefore(legacyDate)).collect(Collectors.toSet());

        final long newResolvedIssues = issuesFilteredByLegacyDate.stream()
                // checks that the issue has not been mapped already to any user
                .filter(issue -> !cardRepository.existsBySonarId(issue.getKey()))
                .map(issue -> issueToScoreCard(userId, issue))
                .map(cardRepository::save).count();

        log.info("{} new score cards stored for user {}", newResolvedIssues, userId);
    }

    private static ScoreCard issueToScoreCard(final String userId, final Issue issue) {
        final long debt = Optional.ofNullable(issue.getDebt())
                .map(Utils::durationTranslator).map(Duration::parse)
                .map(Duration::toMinutes).orElse(0L);
        final SeverityType severityType = Optional.ofNullable(issue.getSeverity()).map(SeverityType::valueOf)
                .orElse(SeverityType.MAJOR);
        return new ScoreCard(UUID.randomUUID().toString(),
                issue.getKey(), userId, Instant.now(), severityType.getScore(), (int) debt, severityType);
    }
}
