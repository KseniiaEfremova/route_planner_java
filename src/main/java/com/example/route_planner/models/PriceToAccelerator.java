package com.example.route_planner.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "accelerator")
public class PriceToAccelerator {
    private String id;
    private String hu;

    public PriceToAccelerator(String id, String hu) {
        this.id = id;
        this.hu = hu;
    }
    public PriceToAccelerator() {
    }

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "hu")
    public String getHu() {
        return hu;
    }

    public void setHu(String hu) {
        this.hu = hu;
    }

    @Override
    public String toString() {
        return "{ \"id\": \"" + id + "\", \"hu\": \"" + hu + "\" }";
    }
}
