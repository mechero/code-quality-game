package com.thepracticaldeveloper.devgame.modules.retriever.service;

import com.thepracticaldeveloper.devgame.modules.configuration.service.SonarServerConfigurationService;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issue;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Issues;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Paging;
import com.thepracticaldeveloper.devgame.modules.stats.service.BadgeService;
import com.thepracticaldeveloper.devgame.modules.stats.service.ScoreCardService;
import com.thepracticaldeveloper.devgame.modules.users.domain.User;
import com.thepracticaldeveloper.devgame.modules.users.service.UserService;
import com.thepracticaldeveloper.devgame.util.ApiHttpUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

@Service
@EnableAsync
@EnableScheduling
public final class SonarDataRetriever {

    private static final Log log = LogFactory.getLog(SonarDataRetriever.class);
    private static final String GET_ISSUES_COMMAND = "/api/issues/search?organizations={organization}&assignees={assignees}&p={page}&ps={pageSize}";

    private final UserService userService;
    private final ScoreCardService scoreCardService;
    private final BadgeService badgeCardService;
    private final SonarServerConfigurationService configurationService;

    private final String organization;
    private AtomicBoolean retrieving;

    @Autowired
    public SonarDataRetriever(final UserService userService,
                              final ScoreCardService scoreCardService,
                              final BadgeService badgeCardService,
                              final SonarServerConfigurationService configurationService,
                              final @Value("${sonar.organization}") String organization) {
        this.userService = userService;
        this.scoreCardService = scoreCardService;
        this.badgeCardService = badgeCardService;
        this.configurationService = configurationService;
        this.organization = organization;
        this.retrieving = new AtomicBoolean(false);
    }

    @Scheduled(fixedRate = 10 * 60000)
    public void retrieveData() {
        try {
            if (retrieving.compareAndSet(false, true)) {
                // It seems that sonar doesn't allow parallel queries with same user since it creates a register for internal
                // stats and that causes an error when inserting into the database.
                userService.getAllActiveUsers().forEach(
                        new RequestLauncher(scoreCardService, badgeCardService, organization, configurationService.getConfiguration().getUrl(),
                                configurationService.getConfiguration().getToken())
                );
                retrieving.set(false);
            } else {
                log.info("Ignoring retrieval request since a retrieval is already in progress...");
            }
        } catch (final Throwable t) {
            log.error("Failed to retrieve data", t);
            retrieving.set(false);
        }
    }

    private static final class RequestLauncher implements Consumer<User> {

        private final ScoreCardService scoreCardService;
        private final BadgeService badgeCardService;
        private final String sonarOrganization;
        private final String sonarUrl;
        private final String token;

        RequestLauncher(final ScoreCardService scoreCardService, final BadgeService badgeCardService, String sonarOrganization, final String sonarUrl, final String token) {
            this.scoreCardService = scoreCardService;
            this.badgeCardService = badgeCardService;
            this.sonarOrganization = sonarOrganization;
            this.sonarUrl = sonarUrl;
            this.token = token;
        }

        @Override
        public void accept(final User user) {
            try {
                int pageIndex = 1;
                int pageTotal = 1;
                int pageSize = 1;
                Set<Issue> issues = new HashSet<>();
                while (pageTotal == pageSize) {
                    log.trace("Requesting page " + pageIndex);
                    RestTemplate restTemplate = new RestTemplate();
                    HttpEntity<String> request = new HttpEntity<>(getHeaders());
                    URI uri = getResolvedIssuesForAssignee(user.getLogin(), pageIndex);
                    log.trace("URI: " + uri);
                    ResponseEntity<Issues> response = restTemplate.exchange(uri, HttpMethod.GET, request, Issues.class);
                    Paging paging = response.getBody().getPaging();
                    pageTotal = paging.getTotal();
                    pageSize = paging.getPageSize();
                    issues.addAll(response.getBody().getIssues());
                    pageIndex++;
                }
                scoreCardService.saveNewCardsFromIssueList(user.getId(), issues);
                badgeCardService.saveNewBadgesFromIssueList(user.getId(), issues);
            } catch (final HttpServerErrorException serverException) {
                log.error(serverException);
                throw serverException;
            }
        }

        HttpHeaders getHeaders() {
            HttpHeaders httpHeaders;
            if (token != null && !token.trim().isEmpty()) {
                httpHeaders = (ApiHttpUtils.getHeaders(token));
            } else {
                httpHeaders = new HttpHeaders();
            }
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
            return httpHeaders;
        }

        URI getResolvedIssuesForAssignee(final String assignee, final int pageIndex) {
            return UriComponentsBuilder.fromHttpUrl(sonarUrl + GET_ISSUES_COMMAND)
                    .buildAndExpand(sonarOrganization, assignee.toLowerCase() + "," + assignee.toUpperCase(),
                            pageIndex, 500)
                    .toUri();
        }
    }

}
