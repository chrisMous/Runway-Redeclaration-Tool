package uk.ac.soton.comp2211.group37.runwayTool.model;

import java.util.ArrayList;

/**
 * An airport holds information about its name and both physical and logical runways.
 * @authors cb7g20
 */
public class Airport {

    // ICAO 4 letter identifier e.g., EGHI
    private String identifier;

    // Aerodrome Name e.g., Southampton
    private String name;
    private ArrayList<PhysicalRunway> runways;

    /**
     * Create an Airport with a given name and ICAO identifier.
     * @param name
     */
    public Airport(String name, String identifier) {
        this.name = name;
        this.identifier = identifier;
        this.runways = new ArrayList<PhysicalRunway>();
    }

    /**
     * Get the aerodrome name
     * @return The name of the aerodrome
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the ICAO aerodrome identifier
     * @return The ICAO identifier of the aerodrome
     */
    public String getIdentifier() {
        return this.identifier;
    }

    public ArrayList<PhysicalRunway> getRunways() {
        return this.runways;
    }

    /**
     * Adds a runway to the Airport's list of runways.
     * @param rwy
     */
    public void addRunway(PhysicalRunway rwy) {
        this.runways.add(rwy);
    }

}
