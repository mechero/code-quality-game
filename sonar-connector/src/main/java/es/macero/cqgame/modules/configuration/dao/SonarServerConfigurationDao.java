package es.macero.cqgame.modules.configuration.dao;

import es.macero.cqgame.modules.configuration.domain.SonarServerConfiguration;

public interface SonarServerConfigurationDao {

    boolean saveConfiguration(SonarServerConfiguration configuration);

    SonarServerConfiguration getConfiguration();

}
