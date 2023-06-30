package com.example.parking.management.system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_space")
public class ParkingSpace {

    @Id
    @Column(name = "required_space")
    private int requiredSpace;

    @OneToOne
    @JoinColumn(name = "vehicle_number", referencedColumnName = "vehicle_number")
    private Vehicle vehicle;


    public int getRequiredSpace() {

        return requiredSpace;
    }

    public void setRequiredSpace(int requiredSpace) {

        this.requiredSpace = requiredSpace;
    }

    public Vehicle getVehicle() {

        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {

        this.vehicle = vehicle;
    }
}
