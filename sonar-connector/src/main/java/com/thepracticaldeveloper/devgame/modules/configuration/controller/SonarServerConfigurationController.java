package com.thepracticaldeveloper.devgame.modules.configuration.controller;

import com.thepracticaldeveloper.devgame.modules.configuration.domain.SonarServerConfiguration;
import com.thepracticaldeveloper.devgame.modules.configuration.domain.sonar.SonarServerStatus;
import com.thepracticaldeveloper.devgame.modules.configuration.service.SonarServerConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/configuration"})
public class SonarServerConfigurationController {

	private final SonarServerConfigurationService configurationService;

	@Autowired
	public SonarServerConfigurationController(final SonarServerConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@RequestMapping(value = "/server-status", method = RequestMethod.GET)
	public SonarServerStatus getServerStatus() {
		final SonarServerConfiguration configuration = configurationService.getConfiguration();
		return configurationService.checkServerDetails(configuration);
	}

}
