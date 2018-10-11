package com.thepracticaldeveloper.devgame.modules.stats.repository;

import com.thepracticaldeveloper.devgame.modules.stats.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface BadgeCardMongoRepository extends CrudRepository<BadgeCard, String> {

    Stream<BadgeCard> findAllByUserId(final String userId);

}
