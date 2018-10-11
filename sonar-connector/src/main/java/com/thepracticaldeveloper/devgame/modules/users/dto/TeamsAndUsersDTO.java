package com.thepracticaldeveloper.devgame.modules.users.dto;

import java.util.List;
import java.util.Map;

public class TeamsAndUsersDTO {
    private List<TeamAndUserDTO> teams;

    public TeamsAndUsersDTO() {
    }

    public TeamsAndUsersDTO(final List<TeamAndUserDTO> teams) {
        this.teams = teams;
    }

    public List<TeamAndUserDTO> getTeams() {
        return teams;
    }

    @Override
    public String toString() {
        return "TeamsAndUsersDTO{" +
                "teams=" + teams +
                '}';
    }

    public static class TeamAndUserDTO {
        private String name;
        private Map<String, String> users;

        public TeamAndUserDTO() {
        }

        public TeamAndUserDTO(String name, Map<String, String> users) {
            this.name = name;
            this.users = users;
        }

        public String getName() {
            return name;
        }

        public Map<String, String> getUsers() {
            return users;
        }

        @Override
        public String toString() {
            return "TeamAndUserDTO{" +
                    "name='" + name + '\'' +
                    ", users=" + users +
                    '}';
        }
    }

}
