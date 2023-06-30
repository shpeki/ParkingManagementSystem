package com.example.parking.management.system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vehicle_category")
@Data
public class VehicleCategory {

    @Id
    @Column(name = "vehicle_category")
    private String vehicleCategory;

    @Column(name = "day_time_rate")
    private double dayTimeRate;

    @Column(name = "night_time_rate")
    private double nightTimeRate;

    @Column(name = "required_space")
    private int requiredSpace;
}
