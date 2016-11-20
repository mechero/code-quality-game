package es.macero.cqgame.modules.configuration.service;

import es.macero.cqgame.modules.configuration.domain.SonarServerConfiguration;
import es.macero.cqgame.modules.configuration.domain.sonar.SonarServerStatus;

public interface SonarServerConfigurationService {
    SonarServerStatus checkServerDetails(SonarServerConfiguration config);

    boolean checkServerAuthentication(SonarServerConfiguration config);

    SonarServerConfiguration getConfiguration();
}
