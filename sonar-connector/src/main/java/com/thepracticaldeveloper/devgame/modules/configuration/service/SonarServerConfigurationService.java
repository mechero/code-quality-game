package com.thepracticaldeveloper.devgame.modules.configuration.service;

import com.thepracticaldeveloper.devgame.modules.configuration.domain.SonarServerConfiguration;
import com.thepracticaldeveloper.devgame.modules.configuration.domain.sonar.SonarServerStatus;

public interface SonarServerConfigurationService {
    SonarServerStatus checkServerDetails(SonarServerConfiguration config);

    boolean checkServerAuthentication(SonarServerConfiguration config);

    SonarServerConfiguration getConfiguration();
}
