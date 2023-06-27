package com.example.parking.management.system.service;

import com.example.parking.management.system.model.ParkingSpace;
import com.example.parking.management.system.model.Vehicle;
import com.example.parking.management.system.model.dto.VehicleDto;
import com.example.parking.management.system.repository.ParkingSpaceRepository;
import com.example.parking.management.system.repository.VehicleRepository;
import com.example.parking.management.system.util.VehicleCategory;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.time.LocalDateTime;

@Service
public class ParkingService {

    private static final int MAX_CAPACITY = 200;

    private final VehicleRepository vehicleRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;

    public ParkingService(VehicleRepository vehicleRepository, ParkingSpaceRepository parkingSpaceRepository) {

        this.vehicleRepository = vehicleRepository;
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    public int getAvailableSpacesCount() {

        Integer sumOfSpaceNumbers = parkingSpaceRepository.calculateSumOfSpaceNumbers();
        int occupiedSpaces = sumOfSpaceNumbers != null ? sumOfSpaceNumbers : 0;
        
        return MAX_CAPACITY - occupiedSpaces;
    }

    public double calculateDueAmount(Long vehicleId) {

//        TODO find the vehicle(throw error if not exist)
//        TODO get category of the vehicle, day/night and discount card
//        TODO get discount card

        return 0.0;
    }

    private boolean isDayTime(LocalDateTime entryTime, LocalDateTime exitTime) {

//        TODO - logic for calculation of day/night
        return false;
    }

    private double calculateDuration(LocalDateTime entryTime, LocalDateTime exitTime) {

//        TODO - logic for calculation of duration
        return 0.0;
    }

    public String registerVehicle(VehicleDto vehicleDto) throws Exception {

        Vehicle vehicle = new Vehicle();

        int requiredSpaces = getRequiredSpacesForCategory(vehicleDto.getCategory());

        if (requiredSpaces > getAvailableSpacesCount()) {

            throw new Exception("No available spaces");
        }


        vehicle.setVehicleNumber(vehicleDto.getVehicleNumber());
        vehicle.setCategory(vehicleDto.getCategory());
        vehicle.setEntryTime(LocalDateTime.now());
        vehicle.setDiscountCard(vehicleDto.getDiscountCard());

        vehicleRepository.save(vehicle);

        ParkingSpace parkingSpace = new ParkingSpace();

        parkingSpace.setSpaceNumber(requiredSpaces);

        parkingSpace.setVehicleNumber(vehicleDto.getVehicleNumber());

        parkingSpaceRepository.save(parkingSpace);

        return vehicle.getVehicleNumber();
    }

    public void deregisterVehicle(Long vehicleId) {

//        TODO find and delete the vehicle(throw error if not exist)
    }

    private double applyDiscount(double dueAmount, String discountCard) {

//        TODO - logic for calculation of discount
        return 0.0;
    }

    private int getRequiredSpacesForCategory(String category) {

        if (category.equals(VehicleCategory.A.name())) {

            return 1;
        } else if (category.equals(VehicleCategory.B.name())) {

            return 2;
        } else if (category.equals(VehicleCategory.C.name())) {

            return 4;
        } else {

            return -1;
        }
    }
}
