package com.example.parking.management.system.model;

import java.time.LocalDateTime;

public class Vehicle {

    private Long id;

    private String category;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private String discountCard;

    public Vehicle(Long id, String category, LocalDateTime entryTime, LocalDateTime exitTime, String discountCard) {

        this.id = id;
        this.category = category;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.discountCard = discountCard;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
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

    public String getDiscountCard() {

        return discountCard;
    }

    public void setDiscountCard(String discountCard) {

        this.discountCard = discountCard;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", discountCard='" + discountCard + '\'' +
                '}';
    }
}
