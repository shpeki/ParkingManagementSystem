package com.example.parking.management.system.model.dto;

public class VehicleDto {

    private String vehicleNumber;
    private String category;
    private String discountCard;

    public String getVehicleNumber() {

        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {

        this.vehicleNumber = vehicleNumber;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public String getDiscountCard() {

        return discountCard;
    }

    public void setDiscountCard(String discountCard) {

        this.discountCard = discountCard;
    }
}
