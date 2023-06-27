package com.example.parking.management.system.enums;

public enum VehicleCategory {
    A (1),
    B (2),
    C (4);

    private int value;

    VehicleCategory(int value) {

        this.value = value;
    }

    public int getValue() {

        return value;
    }
}
