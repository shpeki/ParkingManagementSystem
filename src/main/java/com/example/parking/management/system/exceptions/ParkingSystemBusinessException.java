package com.example.parking.management.system.exceptions;

import java.io.Serial;

public class ParkingSystemBusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3279070291951661614L;

    public ParkingSystemBusinessException(String message) {

        super(message);
    }

    public ParkingSystemBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
