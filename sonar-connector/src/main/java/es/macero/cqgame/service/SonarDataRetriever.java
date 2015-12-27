package es.macero.cqgame.service;

import es.macero.cqgame.resultbeans.Issue;
import es.macero.cqgame.resultbeans.Issues;
import es.macero.cqgame.resultbeans.Paging;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class SonarDataRetriever {

    private static final Log log = LogFactory.getLog(SonarDataRetriever.class);
    public static final String GET_ISSUES_COMMAND = "/api/issues/search?assignees={assignees}|resolutions={resolutions}|p={page}|ps={pageSize}";

    @Autowired
    SonarStatsService statsService;

    @Value("${sonarUser}")
    private String sonarUser;

    @Value("${sonarPassword}")
    private String sonarPassword;

    @Value("${sonarUrl}")
    private String sonarUrl;

    @Scheduled(fixedRate = 10 * 60000)
    public void retrieveData() {
        // It seems that sonar doesn't allow parallel queries with same user since it creates a register for internal
        // stats and that causes an error when inserting into the database.
        statsService.getIds().stream().forEach(new RequestLauncher(statsService, sonarUrl, sonarUser, sonarPassword));
    }

    private static final class RequestLauncher implements Consumer<String> {

        private SonarStatsService statsService;
        private String sonarUrl;
        private String sonarUser;
        private String sonarPassword;

        public RequestLauncher(SonarStatsService statsService, String sonarUrl, String sonarUser, String sonarPassword) {
            this.statsService = statsService;
            this.sonarUrl = sonarUrl;
            this.sonarUser = sonarUser;
            this.sonarPassword = sonarPassword;
        }

        @Override
        public void accept(String id) {
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
            } catch (HttpServerErrorException serverException) {
                log.error(serverException);
                throw serverException;
            }
        }

        public HttpHeaders getHeaders() {
            if (sonarUser != null && !sonarUser.trim().isEmpty()) {
                return ApiHttpUtils.getHeaders(sonarUser, sonarPassword);
            }else {
                return new HttpHeaders();
            }
        }

        public URI getResolvedIssuesForAssignee(String assignee, int pageIndex) {
            URI uri = UriComponentsBuilder.fromHttpUrl(sonarUrl + GET_ISSUES_COMMAND)
                    .buildAndExpand(assignee.toLowerCase() + "," + assignee.toUpperCase(), "FIXED", pageIndex, 500)
                    .toUri();
            System.out.println(uri);
            return uri;
        }
    }

}
