package com.thepracticaldeveloper.devgame.modules.users.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public final class User {

    public static final String NO_TEAM_ASSIGNED = "--NO-TEAM--";

    @Id
    private String id;
    @Indexed
    private String login;
    private String alias;
    @Indexed
    private String team;

    public User() {
    }

    public User(String id, String login, String alias, String team) {
        super();
        this.id = id;
        this.login = login;
        this.alias = alias;
        this.team = team;
    }

    public User(String id) {
        this(id, null, null, null);
    }

    public String getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getTeam() {
        return team;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "SonarUser{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", alias='" + alias + '\'' +
                ", team='" + team + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User sonarUser = (User) o;

        if (id != null ? !id.equals(sonarUser.id) : sonarUser.id != null) return false;
        if (login != null ? !login.equals(sonarUser.login) : sonarUser.login != null) return false;
        if (alias != null ? !alias.equals(sonarUser.alias) : sonarUser.alias != null) return false;
        return team != null ? team.equals(sonarUser.team) : sonarUser.team == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }
}
