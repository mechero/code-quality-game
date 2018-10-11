package com.thepracticaldeveloper.devgame.modules.users.dto;

import javax.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank
    private String alias;
    @NotBlank
    private String login;
    private String team;

    public UserDTO() {
    }

    public UserDTO(final String alias, final String login, final String team) {
        this.alias = alias;
        this.login = login;
        this.team = team;
    }

    public String getAlias() {
        return alias;
    }

    public String getLogin() {
        return login;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "alias='" + alias + '\'' +
                ", login='" + login + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
