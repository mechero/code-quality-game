package com.thepracticaldeveloper.devgame.app;

import com.thepracticaldeveloper.devgame.modules.stats.controller.SonarStatsController;
import com.thepracticaldeveloper.devgame.modules.stats.service.SonarStatsService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class ApplicationTest {

    @Bean
    public SonarStatsService service() {
        return Mockito.mock(SonarStatsService.class);
    }

    @Bean
    public SonarStatsController controller(SonarStatsService service) {
        return new SonarStatsController(service);
    }

}
