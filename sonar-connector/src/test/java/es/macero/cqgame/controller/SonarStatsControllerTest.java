package es.macero.cqgame.controller;

import es.macero.cqgame.app.ApplicationTest;
import es.macero.cqgame.domain.stats.SonarStatsRow;
import es.macero.cqgame.domain.stats.SonarStatsRowBuilder;
import es.macero.cqgame.service.SonarStatsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = ApplicationTest.class)
public class SonarStatsControllerTest{

	private static final String TEAM_ONE = "Team One";
	private static final String JOHN_ONE = "John One";
	private static final String TEAM_TWO = "Team Two";
	private static final String JOHN_TWO = "John Two";
	private static final int INFO = 50;
	private static final int MINOR = 40;
	private static final int MAJOR = 30;
	private static final int CRITICAL = 20;
	private static final int BLOCKER = 10;
	private static final int TOTAL_PAID_DEBT = 100;
	private static final int TOTAL_POINTS = 25;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	SonarStatsService sonarStatsService;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testGetUsers() throws Exception {
		SonarStatsRow statsRow1 = new SonarStatsRowBuilder(JOHN_ONE, TEAM_ONE).withTotalPoints(TOTAL_POINTS).withTotalPaidDebt(TOTAL_PAID_DEBT).withBlocker(BLOCKER).withCritical(CRITICAL).withMajor(MAJOR).withMinor(MINOR).withInfo(INFO).withBadges(new ArrayList<>()).createSonarStatsRow();
		SonarStatsRow statsRow2 = new SonarStatsRowBuilder(JOHN_TWO, TEAM_TWO).withTotalPoints(TOTAL_POINTS).withTotalPaidDebt(TOTAL_PAID_DEBT).withBlocker(BLOCKER).withCritical(CRITICAL).withMajor(MAJOR).withMinor(MINOR).withInfo(INFO).withBadges(new ArrayList<>()).createSonarStatsRow();
		List<SonarStatsRow> expectedStatsRows = Arrays.asList(statsRow1, statsRow2);

		Mockito.when(sonarStatsService.getSortedStatsPerUser()).thenReturn(expectedStatsRows);
		
		this.mvc.perform(get("/legacykillers/users")).andExpect(status().isOk()).andExpect(view().name("sonarstats"))
				.andExpect(model().attribute("stats", expectedStatsRows));
	}

}
