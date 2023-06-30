package com.example.parking.management.system.repository;

import com.example.parking.management.system.model.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard, String> {

    DiscountCard findByCardType(String cardType);

    List<DiscountCard> findAll();
 }
