package com.thepracticaldeveloper.devgame.modules.configuration.domain;

public class SonarServerConfiguration {
    private String url;
    private String token;

    public SonarServerConfiguration() {
    }

    public SonarServerConfiguration(final String url, final String token) {
        this.url = url;
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "SonarServerConfiguration{" +
                "url='" + url + '\'' +
                ", token_exists='" + (token != null) + '\'' +
                '}';
    }
}
