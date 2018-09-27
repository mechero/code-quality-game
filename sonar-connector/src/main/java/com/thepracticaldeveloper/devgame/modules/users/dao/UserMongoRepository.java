package com.thepracticaldeveloper.devgame.modules.users.dao;

import com.thepracticaldeveloper.devgame.modules.users.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserMongoRepository extends CrudRepository<User, String> {

    Optional<User> findUserByLogin(final String login);

    @Query("{'team': {$not: {$eq: '" + User.NO_TEAM_ASSIGNED + "'}}}")
    Stream<User> findAllUsersWithTeam();

    Iterable<User> findAll(final Sort sort);

}
