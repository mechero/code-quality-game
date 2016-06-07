package es.macero.cqgame.modules.retriever.service;

import es.macero.cqgame.modules.configuration.service.SonarServerConfigurationService;
import es.macero.cqgame.modules.sonarapi.resultbeans.Issue;
import es.macero.cqgame.modules.sonarapi.resultbeans.Issues;
import es.macero.cqgame.modules.sonarapi.resultbeans.Paging;
import es.macero.cqgame.modules.stats.service.SonarStatsService;
import es.macero.cqgame.util.ApiHttpUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
@EnableAsync
@EnableScheduling
final class SonarDataRetriever {

	private static final Log log = LogFactory.getLog(SonarDataRetriever.class);
	private static final String GET_ISSUES_COMMAND = "/api/issues/search?assignees={assignees}|resolutions={resolutions}|p={page}|ps={pageSize}";

	private final SonarStatsService statsService;
	private final SonarServerConfigurationService configurationService;

	@Autowired
	public SonarDataRetriever(final SonarStatsService statsService, final SonarServerConfigurationService configurationService) {
		this.statsService = statsService;
		this.configurationService = configurationService;
	}

	@Scheduled(fixedRate = 10 * 60000)
	public void retrieveData() {
		// It seems that sonar doesn't allow parallel queries with same user since it creates a register for internal
		// stats and that causes an error when inserting into the database.
		statsService.getIds().stream().forEach(
				new RequestLauncher(statsService, configurationService.getConfiguration().getUrl(),
						configurationService.getConfiguration().getUser(),
						configurationService.getConfiguration().getPassword()));
	}

	private static final class RequestLauncher implements Consumer<String> {

		private SonarStatsService statsService;
		private String sonarUrl;
		private String sonarUser;
		private String sonarPassword;

		RequestLauncher(final SonarStatsService statsService, final String sonarUrl, final String sonarUser, final String sonarPassword) {
			this.statsService = statsService;
			this.sonarUrl = sonarUrl;
			this.sonarUser = sonarUser;
			this.sonarPassword = sonarPassword;
		}

		@Override
		public void accept(final String id) {
			try {
				int pageIndex = 1;
				int totalPages = 1;
				List<Issue> issues = new ArrayList<>();
				while (pageIndex <= totalPages) {
					RestTemplate restTemplate = new RestTemplate();
					HttpEntity<String> request = new HttpEntity<>(getHeaders());
					URI uri = getResolvedIssuesForAssignee(id, pageIndex);
					ResponseEntity<Issues> response = restTemplate.exchange(uri, HttpMethod.GET, request, Issues.class);
					if (pageIndex == 1) {
						Paging paging = response.getBody().getPaging();
						totalPages = paging.getTotal();
					}
					issues.addAll(response.getBody().getIssues());
					pageIndex++;
				}
				statsService.updateStats(id, issues);
			} catch (final HttpServerErrorException serverException) {
				log.error(serverException);
				throw serverException;
			}
		}

		HttpHeaders getHeaders() {
			if (sonarUser != null && !sonarUser.trim().isEmpty()) {
				return ApiHttpUtils.getHeaders(sonarUser, sonarPassword);
			} else {
				return new HttpHeaders();
			}
		}

		URI getResolvedIssuesForAssignee(final String assignee, final int pageIndex) {
			URI uri = UriComponentsBuilder.fromHttpUrl(sonarUrl + GET_ISSUES_COMMAND)
				.buildAndExpand(assignee.toLowerCase() + "," + assignee.toUpperCase(), "FIXED", pageIndex, 500)
				.toUri();
			System.out.println(uri);
			return uri;
		}
	}

}
