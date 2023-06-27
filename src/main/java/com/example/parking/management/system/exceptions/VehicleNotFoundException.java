package com.example.parking.management.system.exceptions;

import java.io.Serial;

public class VehicleNotFoundException extends Exception{

    @Serial
    private static final long serialVersionUID = 2872992642633261039L;

    public VehicleNotFoundException(String message) {
        super(message);
    }
}
