package com.example.parking.management.system.controller;

import com.example.parking.management.system.exceptions.NoAvailableSpacesException;
import com.example.parking.management.system.exceptions.ParkingSystemBusinessException;
import com.example.parking.management.system.exceptions.VehicleAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.DigestException;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    @ExceptionHandler(ParkingSystemBusinessException.class)
    public ResponseEntity<String> handleParkingSystemBusinessException(ParkingSystemBusinessException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoAvailableSpacesException.class)
    public ResponseEntity<String> handleNoAvailableSpacesException(NoAvailableSpacesException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("There are no available spaces, the parking is full");
    }

    @ExceptionHandler(VehicleAlreadyRegisteredException.class)
    public ResponseEntity<String> handleVehicleAlreadyRegisteredException(VehicleAlreadyRegisteredException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The vehicle is already registered");
    }

    @ExceptionHandler(DigestException.class)
    public ResponseEntity<String> handleDigestException(DigestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The vehicle is already registered");
    }
}
