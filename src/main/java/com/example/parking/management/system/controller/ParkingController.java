package com.example.parking.management.system.controller;

import com.example.parking.management.system.model.Vehicle;
import com.example.parking.management.system.model.dto.VehicleDto;
import com.example.parking.management.system.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping("/due-amount/{vehicleId}")
    public double calculateAmount(@PathVariable Long vehicleId) {

        return parkingService.calculateDueAmount(vehicleId);
    }

    @PostMapping(value = "/register-vehicle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String registerVehicle(@RequestBody VehicleDto vehicle) {

        return parkingService.registerVehicle(vehicle);
    }

    @DeleteMapping("/deregister-vehicle/{vehicleId}")
    public void deregisterVehicle(@PathVariable Long vehicleId) {

        parkingService.deregisterVehicle(vehicleId);
    }


    @GetMapping("/ping")
    public String getPing() {

        return PONG;
    }
}
