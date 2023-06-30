package com.example.parking.management.system.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle")
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


    public String getVehicleNumber() {

        return vehicleNumber;
    }

    public void setVehicleNumber(String id) {

        this.vehicleNumber = id;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public LocalDateTime getEntryTime() {

        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {

        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {

        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {

        this.exitTime = exitTime;
    }

    public String getDiscountCardType() {

        return discountCardType;
    }

    public void setDiscountCardType(String discountCardType) {

        this.discountCardType = discountCardType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + vehicleNumber +
                ", category='" + category + '\'' +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", discountCard='" + discountCardType + '\'' +
                '}';
    }
}
