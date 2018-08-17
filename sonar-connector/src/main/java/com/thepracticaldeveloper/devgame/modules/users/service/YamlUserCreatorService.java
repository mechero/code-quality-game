package com.thepracticaldeveloper.devgame.modules.users.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.thepracticaldeveloper.devgame.modules.users.dao.UserMongoRepository;
import com.thepracticaldeveloper.devgame.modules.users.dao.TeamMongoRepository;
import com.thepracticaldeveloper.devgame.modules.users.domain.User;
import com.thepracticaldeveloper.devgame.modules.users.domain.Team;
import com.thepracticaldeveloper.devgame.modules.users.dto.TeamsAndUsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class YamlUserCreatorService {

    private static final Logger log = LoggerFactory.getLogger(YamlUserCreatorService.class);
    private static final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

    private final UserMongoRepository userRepository;
    private final TeamMongoRepository teamRepository;

    public YamlUserCreatorService(final UserMongoRepository userRepository, final TeamMongoRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    public String createTeamsAndUsersFromYaml(final String yaml) throws IOException {
        try {
            final TeamsAndUsersDTO teamsAndUsersDTO = yamlMapper.readValue(yaml, TeamsAndUsersDTO.class);
            final Set<String> teams = new HashSet<>();
            final List<String> addedUsers = new ArrayList<>();
            final List<String> addedTeams = new ArrayList<>();
            // Add users
            for (TeamsAndUsersDTO.TeamAndUserDTO dto : teamsAndUsersDTO.getTeams()) {
                teams.add(dto.getName());
                for (Map.Entry<String, String> keyValue : dto.getUsers().entrySet()) {
                    final Optional<User> existingUser = userRepository.findUserByLogin(keyValue.getKey());
                    if (existingUser.isPresent()) {
                        if (!existingUser.get().getAlias().equals(keyValue.getValue()) ||
                                !existingUser.get().getTeam().equals(dto.getName())) {
                            userRepository.save(new User(existingUser.get().getId(),
                                    keyValue.getKey(), keyValue.getValue(), dto.getName()));
                            addedUsers.add(keyValue.getKey());
                            log.info("User updated: {}", keyValue.getKey());
                        } else {
                            log.info("Skipping user from YAML file because it already exists: {}", keyValue);
                        }
                    } else {
                        final User sonarUser = new User(UUID.randomUUID().toString(), keyValue.getKey(), keyValue.getValue(), dto.getName());
                        userRepository.save(sonarUser);
                        addedUsers.add(sonarUser.getLogin());
                    }
                }
            }
            // Add teams
            for (String team : teams) {
                final Optional<Team> existingTeam = teamRepository.findByName(team);
                if (!existingTeam.isPresent()) {
                    teamRepository.save(new Team(UUID.randomUUID().toString(), team));
                    addedTeams.add(team);
                }
            }
            return String.format("Added teams [%s]: %s / Added or modified users [%s]: %s",
                    addedTeams.size(), addedTeams, addedUsers.size(), addedUsers.toString());
        } catch (final IOException e) {
            log.error("Error while trying to add YAML users, contents: {}", yaml, e);
            throw e;
        }
    }
}
