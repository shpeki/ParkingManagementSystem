package com.example.parking.management.system.exceptions;

import java.io.Serial;

public class VehicleAlreadyRegisteredException extends Exception{

    @Serial
    private static final long serialVersionUID = -6383087710335355820L;

    public VehicleAlreadyRegisteredException(String message) {
        super(message);
    }
}
