package com.thepracticaldeveloper.devgame.modules.configuration.domain;

public class SonarServerConfiguration {
    private String url;
    private String token;
    private String organization;

    public SonarServerConfiguration() {
    }

    public SonarServerConfiguration(final String url, final String token, final String organization) {
        this.url = url;
        this.token = token;
        this.organization = organization;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    public String getOrganization() {
        return organization;
    }

    @Override
    public String toString() {
        return "SonarServerConfiguration{" +
                "url='" + url + '\'' +
                ", token='" + token + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}
