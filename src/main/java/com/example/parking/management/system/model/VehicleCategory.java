package com.example.parking.management.system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicle_category")
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

    public String getVehicleCategory() {

        return vehicleCategory;
    }

    public void setVehicleCategory(String vehicleCategory) {

        this.vehicleCategory = vehicleCategory;
    }

    public double getDayTimeRate() {

        return dayTimeRate;
    }

    public void setDayTimeRate(double dayTimeRate) {

        this.dayTimeRate = dayTimeRate;
    }

    public double getNightTimeRate() {

        return nightTimeRate;
    }

    public void setNightTimeRate(double nightTimeRate) {

        this.nightTimeRate = nightTimeRate;
    }

    public int getRequiredSpace() {

        return requiredSpace;
    }

    public void setRequiredSpace(int requiredSpace) {

        this.requiredSpace = requiredSpace;
    }
}
