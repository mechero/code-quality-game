package com.thepracticaldeveloper.devgame.modules.users.controller;

import com.thepracticaldeveloper.devgame.modules.users.dao.UserMongoRepository;
import com.thepracticaldeveloper.devgame.modules.users.domain.User;
import com.thepracticaldeveloper.devgame.modules.users.dto.MessageResponseDTO;
import com.thepracticaldeveloper.devgame.modules.users.dto.UserDTO;
import com.thepracticaldeveloper.devgame.modules.users.service.YamlUserCreatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserMongoRepository userRepository;
    private final YamlUserCreatorService userCreatorService;

    public UserController(final UserMongoRepository userRepository, final YamlUserCreatorService userCreatorService) {
        this.userRepository = userRepository;
        this.userCreatorService = userCreatorService;
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping(params = "assigned")
    public ResponseEntity<Iterable<User>> getAllAssignedUsersByTeam() {
        return ResponseEntity.ok(userRepository.findAll(Sort.by("team").ascending()));
    }

    @GetMapping(params = "unassigned")
    public ResponseEntity<Iterable<User>> getAllUnassignedUsers() {
        return ResponseEntity.ok(userRepository.findAllUnassigned(Sort.by("alias").ascending()));
    }

    @DeleteMapping(params = "unassigned")
    public ResponseEntity<MessageResponseDTO> deleteAllUnassignedUsers() {
        userRepository.deleteAllByTeamIsNull();
        return ResponseEntity.ok(new MessageResponseDTO("All unassigned users have been removed"));
    }

    public ResponseEntity<User> createUser(@Valid @RequestBody final UserDTO userDTO) {
        final Optional<User> existingUser = userRepository.findUserByLogin(userDTO.getLogin());
        if (existingUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            final User sonarUser = new User(UUID.randomUUID().toString(), userDTO.getLogin(), userDTO.getAlias(), userDTO.getTeam());
            return ResponseEntity.ok(userRepository.save(sonarUser));
        }
    }

    @PostMapping("/yaml")
    public ResponseEntity<String> createUsers(@RequestBody final String yaml) {
        try {
            final String message = userCreatorService.createTeamsAndUsersFromYaml(yaml);
            return ResponseEntity.ok(message);
        } catch (final Exception e) {
            log.error("Error while trying to add YAML users, contents: {}", yaml, e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") final String id) {
        return userRepository.findById(id).map(
                ResponseEntity::ok
        ).orElse(
                ResponseEntity.notFound().build()
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") final String id,
                                           @Valid @RequestBody final UserDTO userDTO) {
        log.info("Saving user {}", userDTO);
        return userRepository.findById(id).map(
                oldUser -> userRepository.save(new User(id, userDTO.getLogin(), userDTO.getAlias(), userDTO.getTeam()))
        ).map(
                ResponseEntity::ok
        ).orElse(
                ResponseEntity.notFound().build()
        );
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        userRepository.deleteAll();
        return ResponseEntity.ok("All users deleted");
    }
}
