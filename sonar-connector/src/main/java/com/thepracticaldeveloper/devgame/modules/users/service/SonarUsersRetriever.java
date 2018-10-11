package com.thepracticaldeveloper.devgame.modules.users.service;

import com.thepracticaldeveloper.devgame.modules.configuration.service.SonarServerConfigurationService;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Paging;
import com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.Users;
import com.thepracticaldeveloper.devgame.modules.users.dao.UserMongoRepository;
import com.thepracticaldeveloper.devgame.modules.users.domain.User;
import com.thepracticaldeveloper.devgame.util.ApiHttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
@EnableAsync
@EnableScheduling
final class SonarUsersRetriever {

    private static final Logger log = LoggerFactory.getLogger(SonarUsersRetriever.class);
    private static final String GET_USERS_URL = "/api/users/search?p={page}&ps={pageSize}";
    private static final String GET_USERS_BY_ORG_URL = "/api/organizations/search_members?organization={organization}&p={page}&ps={pageSize}";

    private final UserMongoRepository repository;
    private final SonarServerConfigurationService configurationService;
    private final boolean shouldFilterByOrganization;

    SonarUsersRetriever(final UserMongoRepository repository,
                        final SonarServerConfigurationService configurationService) {
        this.repository = repository;
        this.configurationService = configurationService;
        this.shouldFilterByOrganization = StringUtils.isNotEmpty(configurationService.getConfiguration().getToken()) &&
                StringUtils.isNotEmpty(configurationService.getConfiguration().getOrganization());
    }

    @Scheduled(fixedRate = 30 * 60000)
    public void retrieveData() {
        int pageIndex = 1;
        int pageTotal = 1;
        int pageSize;
        Set<com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.User> users = new HashSet<>();
        int totalProcessed = 0;
        while (totalProcessed == 0 || totalProcessed < pageTotal) {
            log.trace("Retrieving users, page " + pageIndex);
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> request = new HttpEntity<>(getHeaders());
            URI uri = buildGetUsersURI(pageIndex);
            log.trace("URI: " + uri);
            try {
                ResponseEntity<Users> response = restTemplate.exchange(uri, HttpMethod.GET, request, Users.class);
                Paging paging = response.getBody().getPaging();
                pageTotal = paging.getTotal();
                pageSize = paging.getPageSize();
                users.addAll(response.getBody().getUsers());
                pageIndex++;
                totalProcessed += pageSize;
                if (pageSize == 0) break;
            } catch (final HttpClientErrorException httpEx) {
                if (httpEx.getStatusCode().is4xxClientError()) {
                    log.warn("Server responded with client error status: {} {}",
                            httpEx.getRawStatusCode(), httpEx.getResponseBodyAsString());
                    break;
                }
            } catch (final Exception e) {
                log.error("Error while trying to retrieve users from SonarQube, page {}", pageIndex, e);
                break;
            }
        }
        final int addedUsers = processUserList(users);
        log.info("Total users processed: {}", totalProcessed);
        log.info("Total added users: {}", addedUsers);

    }

    private int processUserList(final Set<com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.User> users) {
        var addedUsers = 0;
        for (final com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans.User user : users) {
            final Optional<User> matchingUser = repository.findUserByLogin(user.getLogin());
            if (!matchingUser.isPresent()) {
                log.info("Adding new user: {}", user.getLogin());
                repository.save(
                        new User(UUID.randomUUID().toString(), user.getLogin(), user.getName(), null)
                );
                addedUsers++;
            }
        }
        return addedUsers;
    }

    private URI buildGetUsersURI(final int pageIndex) {
        if (shouldFilterByOrganization) {
            return UriComponentsBuilder.fromHttpUrl(configurationService.getConfiguration().getUrl() + GET_USERS_BY_ORG_URL)
                    .buildAndExpand(configurationService.getConfiguration().getOrganization(), pageIndex, 500)
                    .toUri();
        } else {
            return UriComponentsBuilder.fromHttpUrl(configurationService.getConfiguration().getUrl() + GET_USERS_URL)
                    .buildAndExpand(pageIndex, 500)
                    .toUri();
        }
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders;
        var token = configurationService.getConfiguration().getToken();
        if (token != null && !token.trim().isEmpty()) {
            httpHeaders = (ApiHttpUtils.getHeaders(token));
        } else {
            httpHeaders = new HttpHeaders();
        }
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        return httpHeaders;
    }

}
