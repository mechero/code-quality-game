package es.macero.cqgame.modules.configuration.service;

import es.macero.cqgame.modules.configuration.dao.SonarServerConfigurationDao;
import es.macero.cqgame.modules.configuration.domain.SonarServerConfiguration;
import es.macero.cqgame.modules.configuration.domain.sonar.SonarAuthenticationResponse;
import es.macero.cqgame.modules.configuration.domain.sonar.SonarServerStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class SonarServerConfigurationServiceTest {

    @Mock
    private SonarServerConfigurationDao sonarServerConfigurationDaoMock;

    @Mock
    private RestTemplate restTemplateMock;

    private SonarServerConfigurationService configurationService;
    private SonarServerConfiguration config;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        configurationService = new SonarServerConfigurationServiceImpl(sonarServerConfigurationDaoMock, restTemplateMock);
        config = new SonarServerConfiguration("http://localhost:9000", "user", "pass");
    }

    @Test
    public void testConnectsToServer() {
        final SonarServerStatus body = new SonarServerStatus(SonarServerStatus.Key.UP);
        when(restTemplateMock.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(SonarServerStatus.class)))
                .thenReturn(new ResponseEntity<>(body, HttpStatus.OK));
        final SonarServerStatus serverStatus = configurationService.checkServerDetails(config);
        assertEquals(SonarServerStatus.STATUS_UP, serverStatus.getStatus());
    }

    @Test
    public void testServerIsDown() {
        when(restTemplateMock.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(SonarServerStatus.class)))
                .thenThrow(new ResourceAccessException("Can't connect"));
        final SonarServerStatus serverStatus = configurationService.checkServerDetails(config);
        assertEquals(SonarServerStatus.Key.CONNECTION_ERROR.toString(), serverStatus.getStatus());
    }

    @Test
    public void testBadAuthentication() {
        when(restTemplateMock.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(SonarServerStatus.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
        final SonarServerStatus serverStatus = configurationService.checkServerDetails(config);
        assertEquals(SonarServerStatus.Key.UNAUTHORIZED.toString(), serverStatus.getStatus());
    }

    @Test
    public void testUnknownError() {
        when(restTemplateMock.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(SonarServerStatus.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        final SonarServerStatus serverStatus = configurationService.checkServerDetails(config);
        assertEquals(SonarServerStatus.Key.UNKNOWN_ERROR.toString(), serverStatus.getStatus());
    }

    @Test
    public void testCheckAuthenticationIsValid() {
        final SonarAuthenticationResponse response = new SonarAuthenticationResponse(true);
        when(restTemplateMock.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(SonarAuthenticationResponse.class)))
                .thenReturn(new ResponseEntity<>(response, HttpStatus.OK));
        configurationService.checkServerAuthentication(config);
        assertTrue(response.isValid());
    }

    @Test
    public void testCheckAuthenticationIsInvalid() {
        final SonarAuthenticationResponse response = new SonarAuthenticationResponse(false);
        when(restTemplateMock.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(SonarAuthenticationResponse.class)))
                .thenReturn(new ResponseEntity<>(response, HttpStatus.OK));
        assertFalse(response.isValid());
    }

}