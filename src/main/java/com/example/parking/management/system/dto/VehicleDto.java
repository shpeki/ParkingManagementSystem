package com.example.parking.management.system.dto;

import com.example.parking.management.system.enums.DiscountCard;
import com.example.parking.management.system.enums.VehicleCategory;

public class VehicleDto {

    private String vehicleNumber;
    private VehicleCategory category;
    private DiscountCard discountCard;

    public String getVehicleNumber() {

        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {

        this.vehicleNumber = vehicleNumber;
    }

    public VehicleCategory getCategory() {

        return category;
    }

    public void setCategory(VehicleCategory category) {

        this.category = category;
    }

    public DiscountCard getDiscountCard() {

        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {

        this.discountCard = discountCard;
    }
}
