package com.thepracticaldeveloper.devgame.modules.users.dao;

import com.thepracticaldeveloper.devgame.modules.users.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserMongoRepository extends CrudRepository<User, String> {

    Optional<User> findUserByLogin(final String login);

    List<User> findAllByTeam(final String team);

    @Query("{'team': {$not: {$eq: null}}}")
    Stream<User> findAllUsersWithTeam();

    @Query("{'team': {$not: {$eq: null}}}")
    Iterable<User> findAll(final Sort sort);

    @Query("{'team': null}")
    Iterable<User> findAllUnassigned(final Sort sort);

    void deleteAllByTeamIsNull();

}
