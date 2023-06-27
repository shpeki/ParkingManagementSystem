package com.example.parking.management.system.repository;

import com.example.parking.management.system.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Integer> {

}
