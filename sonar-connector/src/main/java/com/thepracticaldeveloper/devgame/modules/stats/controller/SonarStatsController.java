package com.thepracticaldeveloper.devgame.modules.stats.controller;

import com.thepracticaldeveloper.devgame.modules.stats.domain.SonarStatsRow;
import com.thepracticaldeveloper.devgame.modules.stats.service.SonarStatsService;
import com.thepracticaldeveloper.devgame.modules.users.dto.MessageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = {"/stats"})
public class SonarStatsController {
    private SonarStatsService sonarStatsService;

    @Autowired
    public SonarStatsController(final SonarStatsService sonarStatsService) {
        this.sonarStatsService = sonarStatsService;
    }

    @RequestMapping(value = {"/users", ""}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<SonarStatsRow> statsHome() {
        return sonarStatsService.getSortedStatsPerUser();
    }

    @DeleteMapping("/users")
    public MessageResponseDTO deleteAllStats() {
        return sonarStatsService.deleteAllStats();
    }

    @RequestMapping(value = "/teams", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<SonarStatsRow> statsTeams() {
        return sonarStatsService.getSortedStatsPerTeam();
    }
}
