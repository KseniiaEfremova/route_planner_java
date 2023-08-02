package com.example.route_planner.services;

import com.example.route_planner.models.Accelerator;
import com.example.route_planner.models.TripCost;
import com.example.route_planner.repository.AcceleratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcceleratorService {
    private static final int PARKING_COST_PER_DAY = 5;
    private static final float FUEL_COST_PERSONAL_TRANSPORT = 0.3F;
    private static final float FUEL_COST_HTC_TRANSPORT = 0.45F;
    private static final int CAPACITY_PERSONAL_TRANSPORT = 4;
    private static final int CAPACITY_HTC_TRANSPORT = 5;

    @Autowired
    AcceleratorRepository repository;

    public List<Accelerator> list() {
        return (List<Accelerator>) repository.findAll();
    }

    public Accelerator getAcceleratorByID(String id) {
        Optional<Accelerator> acceleratorOptional = repository.findById(id);
        return acceleratorOptional.orElse(null);
    }

    public TripCost getCheapestVehicle(int distance,
                                       int passengers,
                                       int parking) {

        int parkingCostAllDays = parking * PARKING_COST_PER_DAY;
        int numberPersonalShips = (int) Math.ceil((double) passengers / CAPACITY_PERSONAL_TRANSPORT);
        int numberHtcShips = (int) Math.ceil((double) passengers / CAPACITY_HTC_TRANSPORT);

        float htcTransport = (distance * FUEL_COST_HTC_TRANSPORT) * numberHtcShips;
        float personalTransport = (distance * FUEL_COST_PERSONAL_TRANSPORT) * numberPersonalShips + parkingCostAllDays * numberPersonalShips;

        TripCost cheapestVehicle;

        if (htcTransport < personalTransport) {
            cheapestVehicle = new TripCost(TripCost.TransportType.HTC_TRANSPORT, htcTransport);
        } else {
            cheapestVehicle = new TripCost(TripCost.TransportType.PERSONAL_TRANSPORT, personalTransport);
        }
        return cheapestVehicle;
    }
}
