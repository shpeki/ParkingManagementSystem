package com.example.parking.management.system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle")
@Data
public class Vehicle {

    @Id
    @Column(name = "vehicle_number", columnDefinition = "varchar(255)")
    private String vehicleNumber;

    @Column(name = "category")
    private String category;

    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    @Column(name = "discount_card_type")
    private String discountCardType;
}
