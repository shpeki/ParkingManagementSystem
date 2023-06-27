package com.example.parking.management.system.repository;

import com.example.parking.management.system.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class VehicleRepository {

    private Map<Long, Vehicle> vehicles;

    public VehicleRepository() {

        this.vehicles = new HashMap<>();
    }

    public void save(Vehicle vehicle) {

        vehicles.put(vehicle.getId(), vehicle);
    }

    public Vehicle findById(Long id) {

        return vehicles.get(id);
    }

    public void deleteById(Long id) {

        vehicles.remove(id);
    }
}
