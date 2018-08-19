package com.thepracticaldeveloper.devgame.modules.stats.repository;

import com.thepracticaldeveloper.devgame.modules.stats.domain.ScoreCard;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface ScoreCardMongoRepository extends CrudRepository<ScoreCard, String> {

    Optional<ScoreCard> findBySonarId(final String sonarId);

    Stream<ScoreCard> findAllByUserId(final String userId);

    boolean existsBySonarId(final String sonarId);
}
