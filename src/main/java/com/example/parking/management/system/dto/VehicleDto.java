package com.example.parking.management.system.dto;


import lombok.Data;
@Data
public class VehicleDto {

    private String vehicleNumber;
    private String category;
    private String discountCardType;

    public String getVehicleNumber() {

        return vehicleNumber;
    }
}
