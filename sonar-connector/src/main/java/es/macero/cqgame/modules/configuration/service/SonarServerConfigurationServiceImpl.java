package es.macero.cqgame.modules.configuration.service;

import es.macero.cqgame.modules.configuration.dao.SonarServerConfigurationDao;
import es.macero.cqgame.modules.configuration.domain.SonarServerConfiguration;
import es.macero.cqgame.modules.configuration.domain.sonar.SonarAuthenticationResponse;
import es.macero.cqgame.modules.configuration.domain.sonar.SonarServerStatus;
import es.macero.cqgame.util.ApiHttpUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
final class SonarServerConfigurationServiceImpl implements SonarServerConfigurationService {

    private static final Log log = LogFactory.getLog(SonarServerConfigurationServiceImpl.class);
    private static final String API_SYSTEM_STATUS = "/api/system/status";
    private static final String API_AUTHENTICATION_VALIDATE = "/api/authentication/validate";

    private SonarServerConfigurationDao sonarServerConfigurationDao;
    private RestTemplate restTemplate;

    @Autowired
    public SonarServerConfigurationServiceImpl(final SonarServerConfigurationDao sonarServerConfigurationDao, final RestTemplate restTemplate) {
        this.sonarServerConfigurationDao = sonarServerConfigurationDao;
        this.restTemplate = restTemplate;
    }

    /**
     * Checks the current status of the server and the version running on it.
     * It also detects if the server is not reachable.
     *
     * @param config The configuration for Sonar Server
     * @return the response from server
     */
    @Override
    public SonarServerStatus checkServerDetails(final SonarServerConfiguration config) {
        log.info("Trying to reach Sonar server at " + config.getUrl() + API_SYSTEM_STATUS);
        try {
            final HttpHeaders authHeaders = ApiHttpUtils.getHeaders(config.getUser(), config.getPassword());
            HttpEntity<String> request = new HttpEntity<>(authHeaders);
            final ResponseEntity<SonarServerStatus> response = restTemplate
                    .exchange(config.getUrl() + API_SYSTEM_STATUS,
                            HttpMethod.GET, request, SonarServerStatus.class);
            log.info("Response received from server: " + response.getBody());
            return response.getBody();
        } catch (final HttpClientErrorException clientErrorException) {
            if (clientErrorException.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return new SonarServerStatus(SonarServerStatus.Key.UNAUTHORIZED);
            } else {
                return new SonarServerStatus(SonarServerStatus.Key.UNKNOWN_ERROR, clientErrorException.getMessage());
            }
        } catch (final ResourceAccessException resourceAccessException) {
            return new SonarServerStatus(SonarServerStatus.Key.CONNECTION_ERROR, resourceAccessException.getMessage());
        }
    }

    /**
     * Checks if the provided user and password are valid for this server.
     * It assumes that server is reachable, so it may throw Exceptions if it isn't.
     *
     * @param config The server configuration.
     * @return true if the authentication is valid, false otherwise.
     */
    @Override
    public boolean checkServerAuthentication(final SonarServerConfiguration config) {
        log.info("Trying to authenticate with provided user/password...");
        final HttpHeaders authHeaders = ApiHttpUtils.getHeaders(config.getUser(), config.getPassword());
        HttpEntity<String> request = new HttpEntity<>(authHeaders);
        final ResponseEntity<SonarAuthenticationResponse> response = restTemplate
                .exchange(config.getUrl() + API_AUTHENTICATION_VALIDATE,
                        HttpMethod.GET, request, SonarAuthenticationResponse.class);
        log.info("Response to authentication attempt: " + response.getBody());
        return response.getBody().isValid();
    }

    /**
     * Retrieves the server configuration
     *
     * @return the Sonar server configuration object
     */
    @Override
    public SonarServerConfiguration getConfiguration() {
        return sonarServerConfigurationDao.getConfiguration();
    }
}
