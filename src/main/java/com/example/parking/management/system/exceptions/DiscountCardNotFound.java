package com.example.parking.management.system.exceptions;

import java.io.Serial;

public class DiscountCardNotFound extends Exception{

    @Serial
    private static final long serialVersionUID = 881538656609536842L;

    public DiscountCardNotFound(String message) {

        super(message);
    }
}
