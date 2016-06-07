package es.macero.cqgame.modules.configuration.dao;

import es.macero.cqgame.modules.configuration.domain.SonarServerConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

@Component
public class SonarServerConfigurationDaoImpl implements SonarServerConfigurationDao {

    private static final Log log = LogFactory.getLog(SonarServerConfigurationDaoImpl.class);

    private static final String URL_PROPERTY = "url";
    private static final String USER_PROPERTY = "user";
    private static final String PASSWORD_PROPERTY = "password";
    private static final String PROPERTIES_FILE_NAME = "sonar-server.properties";

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
        this.sonarServerConfiguration = readConfigurationFromFile(PROPERTIES_FILE_NAME);
    }

    private SonarServerConfiguration readConfigurationFromFile(final String propertiesFileName) {
        final Properties properties = new Properties();
        try {
            properties.load(new FileReader(propertiesFileName));
            return new SonarServerConfiguration(properties.getProperty(URL_PROPERTY),
                    properties.getProperty(USER_PROPERTY), properties.getProperty(PASSWORD_PROPERTY));
        } catch (final IOException e) {
            log.warn("Configuration file can't be found, using application properties file.");
            return new SonarServerConfiguration(defaultUrl, defaultUser, defaultPassword);
        }
    }

    @Override
    public boolean saveConfiguration(final SonarServerConfiguration configuration) {
        final Properties properties = new Properties();
        properties.setProperty(URL_PROPERTY, configuration.getUrl());
        properties.setProperty(USER_PROPERTY, configuration.getUser());
        properties.setProperty(PASSWORD_PROPERTY, configuration.getPassword());
        try {
            final File file = new File(PROPERTIES_FILE_NAME);
            log.info("Storing properties into " + file.getAbsolutePath());
            properties.store(new FileWriter(file), "Sonar server properties");
            sonarServerConfiguration = configuration;
        } catch (final IOException e) {
            log.error("Error saving configuration", e);
            return false;
        }
        return true;
    }

    @Override
    public SonarServerConfiguration getConfiguration() {
        return sonarServerConfiguration;
    }
}
