package com.thepracticaldeveloper.devgame.modules.users.dto;

import javax.validation.constraints.NotBlank;

public class TeamDTO {

    @NotBlank
    private String name;

    public TeamDTO() {
    }

    public TeamDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
