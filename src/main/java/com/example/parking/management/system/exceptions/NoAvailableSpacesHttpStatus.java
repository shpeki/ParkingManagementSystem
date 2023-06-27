package com.example.parking.management.system.exceptions;

import org.springframework.http.HttpStatus;

public enum NoAvailableSpacesHttpStatus {

    NO_AVAILABLE_SPACES(HttpStatus.CONFLICT, "No available spaces");

    private final int value;
    private final String reasonPhrase;

    NoAvailableSpacesHttpStatus(HttpStatus httpStatus, String reasonPhrase) {
        this.value = httpStatus.value();
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
