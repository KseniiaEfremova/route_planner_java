package com.example.route_planner.models;

public class Connections {
    private String id;
    private Target target;

    public static class Target {
        private String id;
        private  int price;
    }

    public Connections(String id, Target target) {
        this.id = id;
        this.target = target;
    }

    public String getId() {
        return id;
    }

    public Target getTarget() {
        return target;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Connections{" +
                "id='" + id + '\'' +
                ", target=" + target +
                '}';
    }
}
