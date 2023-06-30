package com.example.parking.management.system.repository;

import com.example.parking.management.system.model.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory, String> {

    VehicleCategory findByVehicleCategory(String vehicleCategory);
}
