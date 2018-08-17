package com.thepracticaldeveloper.devgame.modules.users.dao;

import com.thepracticaldeveloper.devgame.modules.users.domain.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeamMongoRepository extends CrudRepository<Team, String> {
    Optional<Team> findByName(String name);
}
