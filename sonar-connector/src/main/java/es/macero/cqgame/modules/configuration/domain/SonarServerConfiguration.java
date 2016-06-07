package es.macero.cqgame.modules.configuration.domain;

public class SonarServerConfiguration {
    private final String url;
    private final String user;
    private final String password;

    public SonarServerConfiguration(final String url, final String user, final String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "SonarServerConfiguration{" +
                "url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
