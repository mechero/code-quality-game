package es.macero.cqgame.modules.configuration.domain.sonar;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class SonarServerStatus {

    public enum Key {
        UP,
        UNAUTHORIZED,
        CONNECTION_ERROR,
        UNKNOWN_ERROR
    }

    public static final String STATUS_UP = "UP";

    private final String id;
    private final String version;
    private final String status;
    private final String message;

    @JsonCreator
    public SonarServerStatus(@JsonProperty("id") String id, @JsonProperty("version") String version, @JsonProperty("status") String status, @JsonProperty("message") final String message) {
        this.id = id;
        this.version = version;
        this.status = status;
        this.message = message;
    }

    public SonarServerStatus(final Key status, final String message) {
        this(null, null, status.name(), message);
    }

    public SonarServerStatus(final Key status) {
        this(null, null, status.name(), null);
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
