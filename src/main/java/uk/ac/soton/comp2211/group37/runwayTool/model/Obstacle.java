package uk.ac.soton.comp2211.group37.runwayTool.model;

import java.io.Serializable;

public class Obstacle implements Serializable {

    /**
     * Name of the obstacle on/near the runway.
     */
    private String name;

    /**
     * Height of the obstacle.
     */
    private double height;

    /**
     * Width of the obstacle
     */
    private double width;

    /**
     * Length of the obstacle
     */
    private double length;

    /**
     * Type of Obstacle present.
     */
    public ObstacleType type;

    public enum ObstacleType {
        AIRCRAFT,
        VEHICLE,
        DEBRIS,
        DEBRIS_SCATTER
    }

    /**
     * Defines a new obstacle along with its characteristics
     * @param height Height of the obstacle
     * @param name Name of the obstacle
     * @param type Type of the obstacle
     */
    public Obstacle (double height, double length, double width, String name, ObstacleType type) {
        this.name = name;
        this.type = type;
        this.height = height;
        this.length = length;
        this.width= width;
    }

    /**
     * This method returns the name of the obstacle.
     */

    public String getName() {
        return name;
    }

    /**
     * This method returns the height of the obstacle.
     */

    public double getHeight(){
        return height;
    }

    /**
     * This method returns the Base (hx50) of the obstacle.
     */
    public double getBase(){
        return height*50;
    }

    /**
     * This method returns the length of the obstacle.
     */

    public double getLength() {
        return length;
    }

    /**
     * This method returns the width of the obstacle.
     */

    public double getWidth() {
        return width;
    }

    // Position data should be independent from the obstacle itself
    //    public int distFromCentre;
    //    public int distFromLThreshold;


}
