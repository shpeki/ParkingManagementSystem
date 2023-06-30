package com.example.parking.management.system.initializer;

import com.example.parking.management.system.model.VehicleCategory;
import com.example.parking.management.system.repository.VehicleCategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleCategoryDataInitializer {

    private final VehicleCategoryRepository vehicleCategoryRepository;

    @Autowired
    public VehicleCategoryDataInitializer(VehicleCategoryRepository vehicleCategoryRepository) {

        this.vehicleCategoryRepository = vehicleCategoryRepository;
    }

    @PostConstruct
    public void init() {

        VehicleCategory categoryA = new VehicleCategory();
        categoryA.setVehicleCategory("A");
        categoryA.setDayTimeRate(3);
        categoryA.setNightTimeRate(2);
        categoryA.setRequiredSpace(1);
        vehicleCategoryRepository.save(categoryA);

        VehicleCategory categoryB = new VehicleCategory();
        categoryB.setVehicleCategory("B");
        categoryB.setDayTimeRate(6);
        categoryB.setNightTimeRate(4);
        categoryB.setRequiredSpace(2);
        vehicleCategoryRepository.save(categoryB);

        VehicleCategory categoryC = new VehicleCategory();
        categoryC.setVehicleCategory("C");
        categoryC.setDayTimeRate(12);
        categoryC.setNightTimeRate(8);
        categoryC.setRequiredSpace(4);
        vehicleCategoryRepository.save(categoryC);
    }
}
