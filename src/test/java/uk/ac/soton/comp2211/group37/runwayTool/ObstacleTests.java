package uk.ac.soton.comp2211.group37.runwayTool;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.ac.soton.comp2211.group37.runwayTool.model.Obstacle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObstacleTests {

    @Test
    @DisplayName("Create obstacle")
    public void createObstacle() {

        var obstacle = new Obstacle(40, 75, 34, "Boeing 737-800 (NG)", Obstacle.ObstacleType.AIRCRAFT);
        assertEquals("Boeing 737-800 (NG)", obstacle.getName(), "Obstacle has correct name");
        assertEquals(40, obstacle.getHeight(), "obstacle has correct height");
        assertEquals(75, obstacle.getLength(), "obstacle has correct length");
        assertEquals(34, obstacle.getWidth(), "obstacle has correct width");

    }

    @Test
    @DisplayName("getBase gives correct length for the base")
    public void obstacleBaseTest() {

        var obstacle = new Obstacle(40, 75, 34, "Boeing 737-800 (NG)", Obstacle.ObstacleType.AIRCRAFT);
        assertEquals(2000, obstacle.getBase(), "Obstacle has correct base");

    }

}
