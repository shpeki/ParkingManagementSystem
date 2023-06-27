package com.example.parking.management.system.controller;

import com.example.parking.management.system.exceptions.NoAvailableSpacesException;
import com.example.parking.management.system.exceptions.NoAvailableSpacesHttpStatus;
import com.example.parking.management.system.exceptions.VehicleAlreadyRegisteredException;
import com.example.parking.management.system.exceptions.VehicleNotFoundException;
import com.example.parking.management.system.model.dto.VehicleDto;
import com.example.parking.management.system.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parking")
public class ParkingController {

    private static final String PONG = "pong";

    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {

        this.parkingService = parkingService;
    }

    @GetMapping("/available-spaces")
    public int getAvailableSpaces() {

        return parkingService.getAvailableSpacesCount();
    }

    @GetMapping("/due-amount/{vehicleNumber}")
    public ResponseEntity<Double> calculateAmount(@PathVariable String vehicleNumber) {

        try {

            return ResponseEntity.ok().body(parkingService.calculateDueAmount(vehicleNumber.toUpperCase()));

        } catch (VehicleNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/register-vehicle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerVehicle(@RequestBody VehicleDto vehicle) {

        try {

            parkingService.registerVehicle(vehicle);
            return ResponseEntity.ok(vehicle.getVehicleNumber().toUpperCase());

        } catch (NoAvailableSpacesException e) {

            return ResponseEntity
                    .status(NoAvailableSpacesHttpStatus
                    .NO_AVAILABLE_SPACES.value())
                    .body(NoAvailableSpacesHttpStatus.NO_AVAILABLE_SPACES.getReasonPhrase());

        } catch (VehicleAlreadyRegisteredException ve) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The vehicle is already registered");
        }
    }

    @DeleteMapping("/deregister-vehicle/{vehicleNumber}")
    public ResponseEntity<Double> deregisterVehicle(@PathVariable String vehicleNumber) {

        try {

            return ResponseEntity.ok().body(parkingService.deregisterVehicle(vehicleNumber.toUpperCase()));

        } catch (VehicleNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ping")
    public String getPing() {

        return PONG;
    }
}
