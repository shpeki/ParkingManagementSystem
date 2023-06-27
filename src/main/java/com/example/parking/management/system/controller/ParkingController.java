package com.example.parking.management.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private static final String PONG = "pong";

    @GetMapping("/ping")
    public String getPing() {

        return PONG;
    }
}
