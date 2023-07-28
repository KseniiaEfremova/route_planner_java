package com.example.route_planner.models;

import java.util.List;

public class Accelerator {
    private String id;
    private String name;
    private List<PriceToAccelerator> connections;

    public Accelerator(String id, String name, List<PriceToAccelerator> connections) {
        this.id = id;
        this.name = String.valueOf(name);
        this.connections = connections;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PriceToAccelerator> getConnections() {
        return connections;
    }
}

