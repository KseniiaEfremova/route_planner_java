package com.example.route_planner.models;

public class TripCost {
    private final TransportType transportType;
    private final float cost;

    public enum TransportType {
        PERSONAL_TRANSPORT,
        HTC_TRANSPORT
    }

    public TripCost(TransportType transportType, float cost) {
        this.transportType = transportType;
        this.cost = cost;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public float getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "TripCost{" +
                "transportType=" + transportType +
                ", cost=" + cost +
                '}';
    }
}
