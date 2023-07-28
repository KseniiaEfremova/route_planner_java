package com.example.route_planner.controllers;

import com.example.route_planner.models.Accelerator;
import com.example.route_planner.services.AcceleratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AcceleratorController {

    private final AcceleratorService acceleratorService;

    public AcceleratorController(AcceleratorService acceleratorService) {
        this.acceleratorService = acceleratorService;
    }

    @GetMapping(path = "accelerators")
    public List<Accelerator> list() {
        return acceleratorService.list();
    }
}
