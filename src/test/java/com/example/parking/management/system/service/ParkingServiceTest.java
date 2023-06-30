package com.example.parking.management.system.service;

import com.example.parking.management.system.dto.VehicleDto;
import com.example.parking.management.system.exceptions.*;
import com.example.parking.management.system.model.ParkingSpace;
import com.example.parking.management.system.model.Vehicle;
import com.example.parking.management.system.model.VehicleCategory;
import com.example.parking.management.system.repository.ParkingSpaceRepository;
import com.example.parking.management.system.repository.VehicleRepository;
import com.example.parking.management.system.repository.DiscountCardRepository;
import com.example.parking.management.system.repository.VehicleCategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ParkingServiceTest {

    private ParkingService parkingService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private ParkingSpaceRepository parkingSpaceRepository;

    @Mock
    private DiscountCardRepository discountCardRepository;

    @Mock
    private VehicleCategoryRepository vehicleCategoryRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        parkingService = new ParkingService(
                vehicleRepository,
                parkingSpaceRepository,
                discountCardRepository,
                vehicleCategoryRepository);
    }

    @Test
    public void testDeregisterVehicle_VehicleNotFoundException() {

        String vehicleNumber = "ABC123";

        when(vehicleRepository.findByVehicleNumber(vehicleNumber)).thenReturn(null);


        Assertions.assertThrows(VehicleNotFoundException.class, () -> parkingService.deregisterVehicle(vehicleNumber));
        verify(vehicleRepository, never()).delete(any(Vehicle.class));
        verify(parkingSpaceRepository, never()).delete(any(ParkingSpace.class));
    }

    @Test
    public void testCalculateDueAmount_VehicleNotFoundException() {

        String vehicleNumber = "ABC123";

        when(vehicleRepository.findByVehicleNumber(vehicleNumber)).thenReturn(null);

        Assertions.assertThrows(VehicleNotFoundException.class, () -> parkingService.calculateDueAmount(vehicleNumber));
    }
}
