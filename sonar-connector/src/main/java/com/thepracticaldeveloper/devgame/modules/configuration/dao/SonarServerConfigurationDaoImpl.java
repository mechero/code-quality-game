package com.thepracticaldeveloper.devgame.modules.configuration.dao;

import com.thepracticaldeveloper.devgame.modules.configuration.domain.SonarServerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SonarServerConfigurationDaoImpl implements SonarServerConfigurationDao {

    private SonarServerConfiguration sonarServerConfiguration;

    private final String sonarUrl;
    private final String sonarToken;
    private final String sonarOrganization;

    @Autowired
    public SonarServerConfigurationDaoImpl(@Value("${sonar.token}") final String sonarToken,
                                           @Value("${sonar.server}") final String sonarUrl,
                                           @Value("${sonar.organization}") final String sonarOrganization) {
        this.sonarUrl = sonarUrl;
        this.sonarToken = sonarToken;
        this.sonarOrganization = sonarOrganization;
        this.sonarServerConfiguration = createSonarServerConfiguration();
    }

    private SonarServerConfiguration createSonarServerConfiguration() {
        return new SonarServerConfiguration(sonarUrl, sonarToken, sonarOrganization);
    }

    @Override
    public SonarServerConfiguration getConfiguration() {
        return sonarServerConfiguration;
    }
}
