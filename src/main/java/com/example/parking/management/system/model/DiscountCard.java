package com.example.parking.management.system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "discount_card")
@Data
public class DiscountCard {

    @Id
    @Column(name = "card_type")
    private String cardType;

    @Column(name = "discount")
    private double discount;

    @Column(name = "description")
    private String description;
}
