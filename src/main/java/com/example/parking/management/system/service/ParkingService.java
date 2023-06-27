package com.example.parking.management.system.service;

import com.example.parking.management.system.exceptions.NoAvailableSpacesException;
import com.example.parking.management.system.exceptions.VehicleAlreadyRegisteredException;
import com.example.parking.management.system.exceptions.VehicleNotFoundException;
import com.example.parking.management.system.model.ParkingSpace;
import com.example.parking.management.system.model.Vehicle;
import com.example.parking.management.system.model.dto.VehicleDto;
import com.example.parking.management.system.repository.ParkingSpaceRepository;
import com.example.parking.management.system.repository.VehicleRepository;
import com.example.parking.management.system.util.DiscountCard;
import com.example.parking.management.system.util.VehicleCategory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public double calculateDueAmount(String vehicleNumber) throws VehicleNotFoundException {

        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);

        if (vehicle == null) {

            throw new VehicleNotFoundException("Vehicle not found");
        }

        LocalDateTime entryTime = vehicle.getEntryTime();
        vehicle.setExitTime(LocalDateTime.now());
        LocalDateTime exitTime = vehicle.getExitTime();

        double hourlyRate;

        if (vehicle.getCategory().equals(VehicleCategory.A.name())) {

            hourlyRate = isDayTime(entryTime, exitTime) ? 3.0 : 2.0;

        } else if (vehicle.getCategory().equals(VehicleCategory.B.name())) {

            hourlyRate = isDayTime(entryTime, exitTime) ? 6.0 : 4.0;

        } else if (vehicle.getCategory().equals(VehicleCategory.C.name())) {

            hourlyRate = isDayTime(entryTime, exitTime) ? 12.0 : 8.0;

        } else {

            return 0.0;
        }

        double duration = calculateDuration(entryTime, exitTime);

        double amount = hourlyRate * duration;

        amount = applyDiscount(vehicle.getDiscountCard(), amount);

        return amount;
    }

    private boolean isDayTime(LocalDateTime entryTime, LocalDateTime exitTime) {

        LocalDateTime daytimeStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
        LocalDateTime daytimeEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(18, 0));

        boolean isDaytime;

        if (exitTime.isBefore(daytimeStart) || exitTime.equals(daytimeStart)) {

            isDaytime = !entryTime.isAfter(exitTime);

        } else if (entryTime.isAfter(daytimeEnd) || entryTime.equals(daytimeEnd)) {

            isDaytime = !exitTime.isBefore(entryTime);

        } else {

            isDaytime = true;
        }

        return isDaytime;

    }

    private double calculateDuration(LocalDateTime entryTime, LocalDateTime exitTime) {

        int entryHour = entryTime.getHour();
        int entryMinute = entryTime.getMinute();
        int exitHour = exitTime.getHour();
        int exitMinute = exitTime.getMinute();

        int hours = exitHour - entryHour;
        int minutes = exitMinute - entryMinute;

        if (minutes < 0) {
            hours--;
            minutes += 60;
        }

        return hours + (minutes / 60.0);
    }

    private double applyDiscount(String discountCard, double dueAmount) {

        if (discountCard.equals(DiscountCard.Silver.name())) {

            return dueAmount * 0.9;

        } else if (discountCard.equals(DiscountCard.Gold.name())) {

            return dueAmount * 0.85;

        } else if (discountCard.equals(DiscountCard.Platinum.name())) {

            return dueAmount * 0.80;

        } else {

            return 0.0;
        }
    }

    public String registerVehicle(VehicleDto vehicleDto)
            throws NoAvailableSpacesException, VehicleAlreadyRegisteredException {

        Vehicle vehicle = new Vehicle();

        int requiredSpaces = getRequiredSpacesForCategory(vehicleDto.getCategory());

        if (requiredSpaces > getAvailableSpacesCount()) {

            throw new NoAvailableSpacesException("No available spaces");
        }

        if (vehicleRepository.findByVehicleNumber(vehicleDto.getVehicleNumber()) != null) {

            throw new VehicleAlreadyRegisteredException("Vehicle is already registered");
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

    public double deregisterVehicle(String vehicleNumber) throws VehicleNotFoundException {

        double dueAmount = calculateDueAmount(vehicleNumber);

        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        ParkingSpace parkingSpace = parkingSpaceRepository.findByVehicleNumber(vehicleNumber);

        vehicleRepository.delete(vehicle);
        parkingSpaceRepository.delete(parkingSpace);

        return dueAmount;
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
