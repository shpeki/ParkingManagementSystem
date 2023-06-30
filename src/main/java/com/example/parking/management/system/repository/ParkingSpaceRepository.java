package com.example.parking.management.system.repository;

import com.example.parking.management.system.model.ParkingSpace;
import com.example.parking.management.system.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Integer> {

    @Query("SELECT SUM(ps.requiredSpace) FROM ParkingSpace ps")
    Integer calculateSumOfSpaceNumbers();

    ParkingSpace findByVehicle_VehicleNumber(String vehicleNumber);

}
