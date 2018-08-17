package com.thepracticaldeveloper.devgame.modules.users.dto;

import javax.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String login;
    private String team;

    public UserDTO() {
    }

    public UserDTO(final String name, final String login, final String team) {
        this.name = name;
        this.login = login;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getTeam() {
        return team;
    }
}
