package com.example.parking.management.system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_spaces")
public class ParkingSpace {

    @Id
    @Column(name = "space_number")
    private int spaceNumber;

    @Column(name = "vehicle_number")
    private String vehicleNumber;


    public int getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(int spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
