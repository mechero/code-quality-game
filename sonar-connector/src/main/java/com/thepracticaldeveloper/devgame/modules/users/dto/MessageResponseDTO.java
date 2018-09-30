package com.thepracticaldeveloper.devgame.modules.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class MessageResponseDTO {
    @JsonProperty("message")
    private final String message;

    public MessageResponseDTO(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageResponseDTO{" +
                "message='" + message + '\'' +
                '}';
    }
}
