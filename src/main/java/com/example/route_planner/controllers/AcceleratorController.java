package com.example.route_planner.controllers;

import com.example.route_planner.models.Accelerator;
import com.example.route_planner.services.AcceleratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/accelerators/{id}")
    public Object getAcceleratorByID(@PathVariable String id) {
        List<Accelerator> accelerators = acceleratorService.list();

        for (Accelerator accelerator : accelerators) {
            if (accelerator.getId().equals(id)) {
                return accelerator;
            }
        }
        return null;
    }

    @GetMapping("/accelerators/{id1}/to/{id2}")
    public int getCheapestRoute(@PathVariable String id1,
                                   @PathVariable String id2) {
        return 0;
    }

    @GetMapping("/transport/{distance}")
    public int getCheapestVehicle(@PathVariable int distance,
                                     @RequestParam(value = "passengers", defaultValue = "1") int passengers,
                                     @RequestParam(value = "parking", defaultValue = "1") int parking) {

        return 0;
    }
}
