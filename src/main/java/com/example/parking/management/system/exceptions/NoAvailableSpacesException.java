package com.example.parking.management.system.exceptions;

import java.io.Serial;

public class NoAvailableSpacesException extends ParkingSystemBusinessException{

    @Serial
    private static final long serialVersionUID = -4325436566483270197L;

    public NoAvailableSpacesException(String message) {
        super(message);
    }
}
