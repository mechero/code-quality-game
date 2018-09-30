package com.thepracticaldeveloper.devgame.modules.users.controller;

import com.thepracticaldeveloper.devgame.modules.users.dao.TeamMongoRepository;
import com.thepracticaldeveloper.devgame.modules.users.domain.Team;
import com.thepracticaldeveloper.devgame.modules.users.domain.User;
import com.thepracticaldeveloper.devgame.modules.users.dto.CreateTeamDTO;
import com.thepracticaldeveloper.devgame.modules.users.dto.MessageResponseDTO;
import com.thepracticaldeveloper.devgame.modules.users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamMongoRepository repository;
    private final UserService userService;

    public TeamController(final TeamMongoRepository repository, final UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Team>> getTeams() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody final CreateTeamDTO teamDTO) {
        final Optional<Team> existingTeam = repository.findByName(teamDTO.getName());
        if (existingTeam.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        } else {
            final Team team = repository.save(new Team(UUID.randomUUID().toString(), teamDTO.getName()));
            return ResponseEntity.ok(team);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteTeam(@PathVariable("id") final String id) {
        final Optional<Team> existingTeam = repository.findById(id);
        if (existingTeam.isPresent()) {
            final List<User> usersInTeam = userService.findUsersByTeam(existingTeam.get().getName());
            if (usersInTeam.isEmpty()) {
                repository.deleteById(existingTeam.get().getId());
                return ResponseEntity.ok(new MessageResponseDTO("Team deleted successfully"));
            } else {
                return ResponseEntity.unprocessableEntity()
                        .body(new MessageResponseDTO("You can't delete a team if it still has players assigned to it."));
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
