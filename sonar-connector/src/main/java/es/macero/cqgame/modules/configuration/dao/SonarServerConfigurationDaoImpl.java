package es.macero.cqgame.modules.configuration.dao;

import es.macero.cqgame.modules.configuration.domain.SonarServerConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SonarServerConfigurationDaoImpl implements SonarServerConfigurationDao {

    private static final Log log = LogFactory.getLog(SonarServerConfigurationDaoImpl.class);

    private SonarServerConfiguration sonarServerConfiguration;

    private final String defaultUrl;
    private final String defaultUser;
    private final String defaultPassword;

    @Autowired
    public SonarServerConfigurationDaoImpl(@Value("${sonarUser}") final String sonarUser,
                                           @Value("${sonarPassword}") final String sonarPassword,
                                           @Value("${sonarUrl}") final String sonarUrl) {
        this.defaultUrl = sonarUrl;
        this.defaultUser = sonarUser;
        this.defaultPassword = sonarPassword;
        this.sonarServerConfiguration = createSonarServerConfiguration();
    }

    private SonarServerConfiguration createSonarServerConfiguration() {
        log.warn("Configuration file can't be found, using application properties file.");
        return new SonarServerConfiguration(defaultUrl, defaultUser, defaultPassword);
    }

    @Override
    public SonarServerConfiguration getConfiguration() {
        return sonarServerConfiguration;
    }
}
