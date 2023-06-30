package com.example.parking.management.system.initializer;

import com.example.parking.management.system.model.DiscountCard;
import com.example.parking.management.system.repository.DiscountCardRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscountCardDataInitializer {

    private DiscountCardRepository discountCardRepository;

    @Autowired
    public DiscountCardDataInitializer(DiscountCardRepository discountCardRepository) {

        this.discountCardRepository = discountCardRepository;
    }

    @PostConstruct
    public void init() {

        DiscountCard silver = new DiscountCard();
        silver.setCardType("Silver");
        silver.setDiscount(10);
        silver.setDescription("Silver discount card with 10% discount");
        discountCardRepository.save(silver);

        DiscountCard gold = new DiscountCard();
        gold.setCardType("Gold");
        gold.setDiscount(15);
        gold.setDescription("Gold discount card with 15% discount");
        discountCardRepository.save(gold);

        DiscountCard platinum = new DiscountCard();
        platinum.setCardType("Platinum");
        platinum.setDiscount(20);
        platinum.setDescription("Platinum discount card with 20% discount" );
        discountCardRepository.save(platinum);
    }
}
