package es.macero.cqgame.modules.stats.controller;

import es.macero.cqgame.modules.stats.domain.SonarStatsRow;
import es.macero.cqgame.modules.stats.service.SonarStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = {"/stats", "/"})
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

    @RequestMapping(value = "/teams", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<SonarStatsRow> statsTeams() {
        return sonarStatsService.getSortedStatsPerTeam();
    }
}
