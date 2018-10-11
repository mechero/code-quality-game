package com.thepracticaldeveloper.devgame.modules.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class MessageResponseDTO {
    @JsonProperty("message")
    private final String message;

    @JsonProperty("error")
    private final boolean error;

    public MessageResponseDTO(final String message, final boolean error) {
        this.message = message;
        this.error = error;
    }

    public MessageResponseDTO(final String message) {
        this(message, false);
    }

    @Override
    public String toString() {
        return "MessageResponseDTO{" +
                "message='" + message + '\'' +
                ", error=" + error +
                '}';
    }
}
