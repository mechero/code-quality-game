package com.thepracticaldeveloper.devgame.modules.code;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CodeService implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(CodeService.class);

    private String codeString;
    private Optional<CodeDetails> codeDetails;

    public CodeService(@Value("${game.code}") final String codeString) {
        this.codeString = codeString;
        this.codeDetails = Optional.empty();
    }

    @Override
    public void afterPropertiesSet() {
        try {
            if (StringUtils.isNotEmpty(codeString)) {
                final CodeParser codeParser = new CodeParser();
                codeDetails = Optional.of(codeParser.getCodeDetails(codeString));
                log.info("Valid code found for {}", codeDetails.get().getName());
            }
        } catch (Exception e) {
            log.error("Error validating the code: " + e.getMessage(), e);
        }
    }

    public Optional<CodeDetails> getCodeDetails() {
        return codeDetails;
    }
}
