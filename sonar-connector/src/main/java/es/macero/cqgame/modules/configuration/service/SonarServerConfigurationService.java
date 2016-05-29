package es.macero.cqgame.modules.configuration.service;

import es.macero.cqgame.modules.configuration.dao.SonarServerConfigurationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final class SonarServerConfigurationService {

    private SonarServerConfigurationDao sonarServerConfigurationDao;

    @Autowired
    public SonarServerConfigurationService(SonarServerConfigurationDao sonarServerConfigurationDao) {
        this.sonarServerConfigurationDao = sonarServerConfigurationDao;
    }
}
