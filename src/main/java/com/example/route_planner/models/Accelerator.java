package com.example.route_planner.models;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import jakarta.persistence.Id;

@DynamoDBTable(tableName = "accelerator")
public class Accelerator {

    private String id;
    private String name;
    private List<PriceToAccelerator> connections;

    public Accelerator(String id, String name, List<PriceToAccelerator> connections) {
        this.id = id;
        this.name = String.valueOf(name);
        this.connections = connections;
    }

    public Accelerator() {

    }

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "connections")
    public List<PriceToAccelerator> getConnections() {
        return connections;
    }

    public void setConnections(List<PriceToAccelerator> connections) {
        this.connections = connections;
    }
}

