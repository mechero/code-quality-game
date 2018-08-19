package com.thepracticaldeveloper.devgame.modules.stats.repository;

import com.thepracticaldeveloper.devgame.modules.stats.domain.ScoreCard;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScoreCardMongoRepository extends CrudRepository<ScoreCard, String> {

    Optional<ScoreCard> findBySonarId(final String sonarId);

    boolean existsBySonarId(final String sonarId);
}
