package com.example.parking.management.system.exceptions;

public class VehicleAlreadyRegisteredException extends ParkingSystemBusinessException{

    public VehicleAlreadyRegisteredException(String message) {
        super(message);
    }
}
