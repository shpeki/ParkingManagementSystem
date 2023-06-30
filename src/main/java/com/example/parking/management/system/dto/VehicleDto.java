package com.example.parking.management.system.dto;


public class VehicleDto {

    private String vehicleNumber;
    private String category;
    private String discountCardType;

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

    public String getDiscountCardType() {

        return discountCardType;
    }

    public void setDiscountCardType(String discountCardType) {

        this.discountCardType = discountCardType;
    }
}
