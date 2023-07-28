package com.example.route_planner.models;

public class PriceToAccelerator {
    private String id;
    private String hu;

    public PriceToAccelerator(String id, String hu) {
        this.id = id;
        this.hu = hu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
