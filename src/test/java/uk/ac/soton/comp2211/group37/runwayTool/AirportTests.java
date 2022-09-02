package uk.ac.soton.comp2211.group37.runwayTool;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uk.ac.soton.comp2211.group37.runwayTool.model.Airport;
import uk.ac.soton.comp2211.group37.runwayTool.model.LogicalRunway;
import uk.ac.soton.comp2211.group37.runwayTool.model.PhysicalRunway;

import java.util.ArrayList;

public class AirportTests {

    @Test
    @DisplayName("Can create an Airport correctly")
    public void createAirport() {
        var airport = new Airport("Southampton", "EGHI");

        assertEquals(airport.getName(), "Southampton", "Airport name should be Southampton");
        assertEquals(airport.getIdentifier(), "EGHI", "Airport identifier should be EGHI");
        assertEquals(airport.getRunways(), new ArrayList<>(), "Initial runway list should be empty");
    }

    @Test
    @DisplayName("Can correctly add runways")
    public void addRunwayTest() {

        var airport = new Airport("Southampton", "EGHI");
        var rwy1 = new LogicalRunway(100, 100, 100, 100, 10, 90, LogicalRunway.RunwayPosition.RIGHT);
        var rwy2 = new LogicalRunway(100, 100, 100, 100, 0, 270, LogicalRunway.RunwayPosition.LEFT);
        var phys = new PhysicalRunway(rwy1, rwy2);

        airport.addRunway(phys);
        assertEquals(phys, airport.getRunways().get(0), "runway should be added");

    }
}
