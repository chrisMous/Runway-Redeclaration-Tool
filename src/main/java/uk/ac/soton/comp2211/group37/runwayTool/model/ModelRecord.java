package uk.ac.soton.comp2211.group37.runwayTool.model;

import java.util.ArrayList;

public class ModelRecord {

    private ArrayList<Airport> airports;
    private ArrayList<Obstacle> obstacles;

    public ModelRecord(ArrayList<Airport> airports, ArrayList<Obstacle> obstacles) {
        this.airports = airports;
        this.obstacles = obstacles;
    }

    public ArrayList<Airport> getAirports() {
        return airports;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
