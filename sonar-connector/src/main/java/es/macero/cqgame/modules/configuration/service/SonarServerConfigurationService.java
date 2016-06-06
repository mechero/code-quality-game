package es.macero.cqgame.modules.configuration.service;

import es.macero.cqgame.modules.configuration.domain.SonarServerConfiguration;
import es.macero.cqgame.modules.configuration.domain.sonar.SonarServerStatus;

/**
 * This Service is not working yet, use the configuration described in README file.
 */
public interface SonarServerConfigurationService {
    SonarServerStatus checkServerDetails(SonarServerConfiguration config);

    boolean checkServerAuthentication(SonarServerConfiguration config);

    boolean saveConfiguration(SonarServerConfiguration configuration);

    SonarServerConfiguration getConfiguration();
}
