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

    private final String sonarUrl;
    private final String sonarToken;

    @Autowired
    public SonarServerConfigurationDaoImpl(@Value("${sonarToken}") final String sonarToken,
                                           @Value("${sonarUrl}") final String sonarUrl) {
        this.sonarUrl = sonarUrl;
        this.sonarToken = sonarToken;
        this.sonarServerConfiguration = createSonarServerConfiguration();
    }

    private SonarServerConfiguration createSonarServerConfiguration() {
        log.warn("Configuration file can't be found, using application properties file.");
        return new SonarServerConfiguration(sonarUrl, sonarToken);
    }

    @Override
    public SonarServerConfiguration getConfiguration() {
        return sonarServerConfiguration;
    }
}
