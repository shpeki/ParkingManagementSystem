package com.example.parking.management.system.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "parking_space")
@Data
public class ParkingSpace {

    @Id
    @Column(name = "required_space")
    private int requiredSpace;

    @OneToOne
    @JoinColumn(name = "vehicle_number", referencedColumnName = "vehicle_number")
    private Vehicle vehicle;
}
