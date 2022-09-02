package uk.ac.soton.comp2211.group37.runwayTool;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.ac.soton.comp2211.group37.runwayTool.model.Airport;
import uk.ac.soton.comp2211.group37.runwayTool.model.ModelRecord;
import uk.ac.soton.comp2211.group37.runwayTool.model.Obstacle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelRecordTests {

    @Test
    @DisplayName("Can create a new model record")
    public void createModelRecordTest() {

        ArrayList<Airport> airports = new ArrayList<>(List.of(new Airport[]{
                new Airport("Southampton", "EGHI"),
                new Airport("Heathrow", "EGLL")
        }));

        ArrayList<Obstacle> obstacles = new ArrayList<>(List.of(new Obstacle[]{
                new Obstacle(10, 10, 10, "Cube", Obstacle.ObstacleType.DEBRIS)
        }));

        var mr = new ModelRecord(airports, obstacles);

        assertEquals(airports, mr.getAirports(), "stores correct airports");
        assertEquals(obstacles, mr.getObstacles(), "stores correct obstacles");

    }

}
