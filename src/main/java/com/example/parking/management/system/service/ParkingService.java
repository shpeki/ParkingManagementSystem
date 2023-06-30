package com.example.parking.management.system.service;

import com.example.parking.management.system.exceptions.DiscountCardNotFound;
import com.example.parking.management.system.exceptions.NoAvailableSpacesException;
import com.example.parking.management.system.exceptions.VehicleAlreadyRegisteredException;
import com.example.parking.management.system.exceptions.VehicleNotFoundException;
import com.example.parking.management.system.model.DiscountCard;
import com.example.parking.management.system.model.ParkingSpace;
import com.example.parking.management.system.model.Vehicle;
import com.example.parking.management.system.dto.VehicleDto;
import com.example.parking.management.system.repository.ParkingSpaceRepository;
import com.example.parking.management.system.repository.VehicleRepository;
import com.example.parking.management.system.repository.DiscountCardRepository;
import com.example.parking.management.system.enums.VehicleCategory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ParkingService {

    private static final int MAX_CAPACITY = 200;

    private final VehicleRepository vehicleRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;

    private final DiscountCardRepository discountCardRepository;

    public ParkingService(
            VehicleRepository vehicleRepository,
            ParkingSpaceRepository parkingSpaceRepository,
            DiscountCardRepository discountCardRepository  ) {

        this.vehicleRepository = vehicleRepository;
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.discountCardRepository = discountCardRepository;

    }

    public int getAvailableSpacesCount() {

        Integer sumOfSpaceNumbers = parkingSpaceRepository.calculateSumOfSpaceNumbers();
        int occupiedSpaces = sumOfSpaceNumbers != null ? sumOfSpaceNumbers : 0;

        return MAX_CAPACITY - occupiedSpaces;
    }

    public double calculateDueAmount(String vehicleNumber) throws VehicleNotFoundException {

        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);

        double totalDueAmount = 0.0;


        if (vehicle == null) {

            throw new VehicleNotFoundException("Vehicle not found");
        }

        LocalDateTime entryTime = vehicle.getEntryTime();
        vehicle.setExitTime(LocalDateTime.now().withSecond(0).withNano(0).plusMinutes(1));
        LocalDateTime exitTime = vehicle.getExitTime();

        double hourlyRate;

        while (entryTime.isBefore(exitTime)) {

            if (vehicle.getCategory().equals(VehicleCategory.A.name())) {

                hourlyRate = isDayTime(entryTime) ? 3.0 : 2.0;
                totalDueAmount += hourlyRate;

            } else if (vehicle.getCategory().equals(VehicleCategory.B.name())) {

                hourlyRate = isDayTime(entryTime) ? 6.0 : 4.0;
                totalDueAmount += hourlyRate;

            } else {

                hourlyRate = isDayTime(entryTime) ? 12.0 : 8.0;
                totalDueAmount += hourlyRate;
            }

            entryTime = entryTime.plusHours(1);

        }

        totalDueAmount = applyDiscount(vehicle.getDiscountCardType(), totalDueAmount);

        return totalDueAmount;
    }

    private boolean isDayTime(LocalDateTime entryTime) {
        LocalTime daytimeStart = LocalTime.of(8, 0);
        LocalTime daytimeEnd = LocalTime.of(18, 0);
        LocalTime entryTimeOfDay = entryTime.toLocalTime();

        return entryTimeOfDay.isAfter(daytimeStart) && entryTimeOfDay.isBefore(daytimeEnd);
    }

    private double applyDiscount(String discountCardType, double dueAmount) {

        List<DiscountCard> discountCards = discountCardRepository.findAll();

        DiscountCard discountCard = discountCards
                .stream()
                .filter(card -> card.getCardType().equals(discountCardType))
                .findFirst()
                .orElse(null);

        double asd = dueAmount * discountCard.getDiscount();


       return dueAmount - dueAmount * (discountCard.getDiscount()/100);
    }

    public String registerVehicle(VehicleDto vehicleDto)
            throws NoAvailableSpacesException,
            VehicleAlreadyRegisteredException,
            DiscountCardNotFound {

        Vehicle vehicle = new Vehicle();

        int requiredSpaces = getRequiredSpacesForCategory(vehicleDto.getCategory().toString());

        if (requiredSpaces > getAvailableSpacesCount()) {

            throw new NoAvailableSpacesException("There are no available spaces, the parking is full");
        }

        if (vehicleRepository.findByVehicleNumber(vehicleDto.getVehicleNumber()) != null) {

            throw new VehicleAlreadyRegisteredException("Vehicle is already registered");
        }

        vehicle.setVehicleNumber(vehicleDto.getVehicleNumber());
        vehicle.setCategory(vehicleDto.getCategory().toString());
        vehicle.setEntryTime(LocalDateTime.now().withSecond(0).withNano(0).plusMinutes(1));
        vehicle.setDiscountCardType(vehicleDto.getDiscountCardType());

        if (discountCardRepository.findByCardType(vehicleDto.getDiscountCardType()) != null) {

            DiscountCard discountCard = discountCardRepository.findByCardType(vehicleDto.getDiscountCardType());
            vehicle.setDiscountCardType(discountCard.getCardType());

        } else {

            throw new DiscountCardNotFound("Discount card not found");
        }

        vehicleRepository.save(vehicle);

        ParkingSpace parkingSpace = new ParkingSpace();

        parkingSpace.setRequiredSpace(requiredSpaces);

        parkingSpace.setVehicle(vehicle);

        parkingSpaceRepository.save(parkingSpace);

        return vehicle.getVehicleNumber();
    }

    public double deregisterVehicle(String vehicleNumber) throws VehicleNotFoundException {

        double dueAmount = calculateDueAmount(vehicleNumber);

        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        ParkingSpace parkingSpace = parkingSpaceRepository.findByVehicle_VehicleNumber(vehicleNumber);

        parkingSpaceRepository.delete(parkingSpace);
        vehicleRepository.delete(vehicle);

        return dueAmount;
    }

    private int getRequiredSpacesForCategory(String category) {

        if (category.equals(VehicleCategory.A.name())) {

            return VehicleCategory.A.getValue();

        } else if (category.equals(VehicleCategory.B.name())) {

            return VehicleCategory.B.getValue();

        } else {

            return VehicleCategory.C.getValue();
        }
    }
}
