package com.example.route_planner.services;

import com.example.route_planner.models.Accelerator;
import com.example.route_planner.models.PriceToAccelerator;
import com.example.route_planner.models.TripCost;
import com.example.route_planner.repository.AcceleratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public int getCheapestRoute(String from, String to) {
        List<Accelerator> accelerators = (List<Accelerator>) repository.findAll();
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        for (Accelerator accelerator : accelerators) {
            Map<String, Integer> connections = new HashMap<>();
            for (PriceToAccelerator connection : accelerator.getConnections()) {
                connections.put(connection.getId(), connection.getHu());
            }
            graph.put(accelerator.getId(), connections);
        }

        Map<String, Integer> distances = new HashMap<>();
        for (String key : graph.keySet()) {
            distances.put(key, -1);
        }
        distances.put(from, 0);
        Set<String> visited = new HashSet<>();

        while (true) {
            int currMinVal = -1;
            String currMinKey = "";
            for (Map.Entry<String, Integer> distance : distances.entrySet()) {
                if (!visited.contains(distance.getKey()) && distance.getValue() >= 0 && (currMinVal == -1 || distance.getValue() < currMinVal)) {
                    currMinVal = distance.getValue();
                    currMinKey = distance.getKey();
                }
            }

            if (currMinVal == -1) {
                break;
            }

            for (Map.Entry<String, Integer> connection : graph.get(currMinKey).entrySet()) {
                int newDistance = currMinVal + connection.getValue();
                int currDistance = distances.get(connection.getKey());
                if (currDistance == -1 || newDistance < currDistance) {
                    distances.put(connection.getKey(), newDistance);
                }
            }

            visited.add(currMinKey);
        }

        return distances.get(to);
    }
}
