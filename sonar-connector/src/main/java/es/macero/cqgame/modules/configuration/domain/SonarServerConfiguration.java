package es.macero.cqgame.modules.configuration.domain;

public class SonarServerConfiguration {
    private String url;
    private String user;
    private String password;

    public SonarServerConfiguration() {
    }

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

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public void setPassword(final String password) {
        this.password = password;
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
