package com.example.parking.management.system.repository;

import com.example.parking.management.system.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByVehicleNumber(String vehicleNumber);
}
