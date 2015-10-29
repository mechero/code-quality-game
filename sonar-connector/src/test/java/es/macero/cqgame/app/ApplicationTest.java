package es.macero.cqgame.app;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

import es.macero.cqgame.controller.SonarStatsController;
import es.macero.cqgame.dao.SonarUserRepository;
import es.macero.cqgame.domain.badges.BadgeCalculator;
import es.macero.cqgame.domain.badges.BadgeEarlyBird;
import es.macero.cqgame.service.SonarStatsService;

public class ApplicationTest {

	@Bean
	public SonarStatsService service() {
		return Mockito.mock(SonarStatsService.class);
	}
	
	@Bean
	public SonarUserRepository repo() {
		return Mockito.mock(SonarUserRepository.class);
	}

	@Bean
	public SonarStatsController controller(SonarStatsService service) {
		return new SonarStatsController(service);
	}
	
	@Bean
	public BadgeCalculator badgeCalculator() {
		return new BadgeEarlyBird();
	}
	
}
