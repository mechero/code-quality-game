package com.thepracticaldeveloper.devgame.modules.users.controller;

import com.thepracticaldeveloper.devgame.modules.users.dao.TeamMongoRepository;
import com.thepracticaldeveloper.devgame.modules.users.domain.Team;
import com.thepracticaldeveloper.devgame.modules.users.dto.TeamDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamMongoRepository repository;

    public TeamController(final TeamMongoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Team>> getTeams() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody final TeamDTO teamDTO) {
        final Optional<Team> existingTeam = repository.findByName(teamDTO.getName());
        if (existingTeam.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            final Team team = repository.save(new Team(UUID.randomUUID().toString(), teamDTO.getName()));
            return ResponseEntity.ok(team);
        }
    }

}
