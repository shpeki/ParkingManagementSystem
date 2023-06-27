package com.example.parking.management.system.enums;

public enum DiscountCard {

    Silver (0.9),

    Gold (0.85),

    Platinum (0.8);

    private double value;

    DiscountCard(double value) {

        this.value = value;
    }

    public double getValue() {

        return value;
    }
}
