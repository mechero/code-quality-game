package es.macero.cqgame.modules.configuration.domain.sonar;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class SonarAuthenticationResponse {

    private boolean valid;

    @JsonCreator
    public SonarAuthenticationResponse(@JsonProperty(value = "valid") boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public String toString() {
        return "SonarAuthenticationResponse{" +
                "valid=" + valid +
                '}';
    }
}
