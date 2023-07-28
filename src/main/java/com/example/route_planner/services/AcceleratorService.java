package com.example.route_planner.services;

import com.example.route_planner.models.Accelerator;
import com.example.route_planner.models.PriceToAccelerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcceleratorService {
    public List<Accelerator> list() {
        List<Accelerator> accelerators = new ArrayList<>();

        List<PriceToAccelerator> connections = new ArrayList<>();
        connections.add(new PriceToAccelerator("SIR", "200"));
        connections.add(new PriceToAccelerator("PRO", "120"));

        accelerators.add(new Accelerator("SOL", "Sol", connections));

        return accelerators;
    }
}
