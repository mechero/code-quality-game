package es.macero.cqgame.app;

import es.macero.cqgame.modules.stats.controller.SonarStatsController;
import es.macero.cqgame.modules.stats.service.SonarStatsService;
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
