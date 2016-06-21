package es.macero.cqgame.modules.configuration.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.macero.cqgame.modules.configuration.domain.SonarServerConfiguration;
import es.macero.cqgame.modules.configuration.service.SonarServerConfigurationService;

@Controller
@RequestMapping(value = {"/configuration"})
public class SonarServerConfigurationController {

	private final SonarServerConfigurationService configurationService;

	@Autowired
	public SonarServerConfigurationController(final SonarServerConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getConfiguration(final Map<String, Object> model) {
		model.put("configuration", configurationService.getConfiguration());
		return "configuration";
	}

	@RequestMapping(value = "/server-status", method = RequestMethod.GET)
	public String getServerStatus(final Map<String, Object> model) {
		final SonarServerConfiguration configuration = configurationService.getConfiguration();
		model.put("configuration", configuration);
		model.put("serverStatus", configurationService.checkServerDetails(configuration));
		return "configuration";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveConfiguration(final Map<String, Object> model, final @ModelAttribute("configuration") SonarServerConfiguration configuration) {
		configurationService.saveConfiguration(configuration);
		model.put("configuration", configurationService.getConfiguration());
		model.put("serverStatus", configurationService.checkServerDetails(configuration));
		return "configuration";
	}

}
