package com.example.parking.management.system.service;

import com.example.parking.management.system.dto.VehicleDto;
import com.example.parking.management.system.enums.DiscountCard;
import com.example.parking.management.system.enums.VehicleCategory;
import com.example.parking.management.system.exceptions.NoAvailableSpacesException;
import com.example.parking.management.system.exceptions.VehicleAlreadyRegisteredException;
import com.example.parking.management.system.exceptions.VehicleNotFoundException;
import com.example.parking.management.system.model.ParkingSpace;
import com.example.parking.management.system.model.Vehicle;
import com.example.parking.management.system.repository.ParkingSpaceRepository;
import com.example.parking.management.system.repository.VehicleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ParkingServiceTest {

    private ParkingService parkingService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private ParkingSpaceRepository parkingSpaceRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        parkingService = new ParkingService(vehicleRepository, parkingSpaceRepository);
    }

    @Test
    public void testRegisterVehicle_Success() throws NoAvailableSpacesException, VehicleAlreadyRegisteredException {

        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleNumber("ABC123");
        vehicleDto.setCategory(VehicleCategory.valueOf("A"));
        vehicleDto.setDiscountCard(DiscountCard.valueOf("Silver"));

        when(parkingSpaceRepository.calculateSumOfSpaceNumbers()).thenReturn(10);
        when(vehicleRepository.findByVehicleNumber(vehicleDto.getVehicleNumber())).thenReturn(null);


        String registeredVehicleNumber = parkingService.registerVehicle(vehicleDto);


        Assertions.assertEquals(vehicleDto.getVehicleNumber(), registeredVehicleNumber);
        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
        verify(parkingSpaceRepository, times(1)).save(any(ParkingSpace.class));
    }

    @Test
    public void testRegisterVehicle_NoAvailableSpacesException() {

        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleNumber("ABC123");
        vehicleDto.setCategory(VehicleCategory.valueOf("A"));
        vehicleDto.setDiscountCard(DiscountCard.valueOf("Silver"));

        when(parkingSpaceRepository.calculateSumOfSpaceNumbers()).thenReturn(200);

        Assertions.assertThrows(NoAvailableSpacesException.class, () -> parkingService.registerVehicle(vehicleDto));
        verify(vehicleRepository, never()).save(any(Vehicle.class));
        verify(parkingSpaceRepository, never()).save(any(ParkingSpace.class));
    }

    @Test
    public void testRegisterVehicle_VehicleAlreadyRegisteredException() {

        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleNumber("ABC123");
        vehicleDto.setCategory(VehicleCategory.valueOf("A"));
        vehicleDto.setDiscountCard(DiscountCard.valueOf("Silver"));

        when(parkingSpaceRepository.calculateSumOfSpaceNumbers()).thenReturn(10);
        when(vehicleRepository.findByVehicleNumber(vehicleDto.getVehicleNumber())).thenReturn(new Vehicle());


        Assertions.assertThrows(VehicleAlreadyRegisteredException.class, () -> parkingService.registerVehicle(vehicleDto));
        verify(vehicleRepository, never()).save(any(Vehicle.class));
        verify(parkingSpaceRepository, never()).save(any(ParkingSpace.class));
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
