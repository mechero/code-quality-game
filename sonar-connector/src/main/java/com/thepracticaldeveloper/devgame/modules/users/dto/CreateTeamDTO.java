package com.thepracticaldeveloper.devgame.modules.users.dto;

import javax.validation.constraints.NotBlank;

public class CreateTeamDTO {

    @NotBlank
    private String name;

    public CreateTeamDTO() {
    }

    public CreateTeamDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
