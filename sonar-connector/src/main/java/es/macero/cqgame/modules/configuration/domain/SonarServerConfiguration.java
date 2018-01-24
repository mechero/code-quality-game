package es.macero.cqgame.modules.configuration.domain;

public class SonarServerConfiguration {
    private String url;
    private String user;

    public SonarServerConfiguration() {
    }

    public SonarServerConfiguration(final String url, final String user) {
        this.url = url;
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SonarServerConfiguration{" +
                "url='" + url + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
