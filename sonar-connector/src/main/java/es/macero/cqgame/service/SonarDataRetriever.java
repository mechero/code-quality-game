package es.macero.cqgame.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

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

import es.macero.cqgame.resultbeans.Issue;
import es.macero.cqgame.resultbeans.Issues;
import es.macero.cqgame.resultbeans.Paging;

@Service
@EnableAsync
@EnableScheduling
public class SonarDataRetriever {

    private static final Log log = LogFactory.getLog(SonarDataRetriever.class);
    public static final String GET_ISSUES_COMMAND = "/api/issues/search";

    @Autowired
    SonarStatsService statsService;

    @Value("${creds}")
    private String base64Creds;

    @Value("${sonarUrl}")
    private String sonarUrl;

    @Scheduled(fixedRate = 10 * 60000)
    public void retrieveData() {
        // It seems that sonar doesn't allow parallel queries with same user since it creates a register for internal
        // stats and that causes an error when inserting into the database.
        statsService.getIds().stream().forEach(new RequestLauncher(statsService, sonarUrl, base64Creds));
    }

    private static final class RequestLauncher implements Consumer<String> {

        private SonarStatsService statsService;
        private String sonarUrl;
        private String creds;

        public RequestLauncher(SonarStatsService statsService, String sonarUrl, String creds) {
            this.statsService = statsService;
            this.sonarUrl = sonarUrl;
            this.creds = creds;
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
                    URI uri = getResolvedIssuesForAssignee(id, true, pageIndex);
                    ResponseEntity<Issues> response = restTemplate.exchange(uri, HttpMethod.GET, request, Issues.class);
                    if (pageIndex == 1) {
                        Paging paging = response.getBody().getPaging();
                        totalPages = paging.getPages();
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
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + creds);
            headers.add("Accept", "application/json");
            return headers;
        }

        public URI getResolvedIssuesForAssignee(String assignee, boolean resolved, int pageIndex) {
            URI uri = UriComponentsBuilder.fromHttpUrl(sonarUrl + GET_ISSUES_COMMAND)
                    .queryParam("assignees", assignee.toLowerCase() + "," + assignee.toUpperCase()).queryParam("resolved", resolved)
                    .queryParam("pageIndex", pageIndex).queryParam("pageSize", 500).build().toUri();
            System.out.println(uri);
            return uri;
        }
    }

}
