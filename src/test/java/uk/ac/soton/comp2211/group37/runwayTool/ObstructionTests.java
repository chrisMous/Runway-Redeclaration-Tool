package uk.ac.soton.comp2211.group37.runwayTool;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.ac.soton.comp2211.group37.runwayTool.model.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import net.jqwik.api.*;

import java.awt.image.RGBImageFilter;

public class ObstructionTests {

    LogicalRunway runway09R = new LogicalRunway(3660, 3660, 3660, 3353, 307, 90, LogicalRunway.RunwayPosition.RIGHT);
    LogicalRunway runway27L = new LogicalRunway(3660, 3660, 3660, 3660, 0, 270, LogicalRunway.RunwayPosition.LEFT);

    LogicalRunway runway09L = new LogicalRunway(3902,3902,3902,3595,306, 90, LogicalRunway.RunwayPosition.LEFT);
    LogicalRunway runway27R = new LogicalRunway(3884,3962,3884,3884,0, 270, LogicalRunway.RunwayPosition.RIGHT);

    // TODO possible implementation error in the code or in this test
    @Disabled
    @Test
    @DisplayName("Heathrow Scenario 1 Calculations")
    public void heathrowScenario109LTest() {

        var obstacle = new Obstacle(12, 75, 34, "Boeing 737-800 (NG)", Obstacle.ObstacleType.AIRCRAFT);
        var obstructed09L_27R = new ObstructedRunway(runway09L, runway27R, 0, -50, 3646);

        // Take off/landing away
        assertEquals(2985, obstructed09L_27R.getNewLda(false, runway09L, obstacle), "LDA away from obstacle");
        assertArrayEquals(new double[]{3346, 3346, 3346}, obstructed09L_27R.getTakeOffDistances(false, runway09L, obstacle), "TORA, TODA, ASDA away from obstacle");
    }

    @Test
    @DisplayName("Heathrow Scenario 1 Calculations")
    public void heathrowScenario127RTest() {

        var obstacle = new Obstacle(12, 75, 34, "Boeing 737-800 (NG)", Obstacle.ObstacleType.AIRCRAFT);
        var obstructed09L_27R = new ObstructedRunway(runway09L, runway27R, 0, -50, 3646);

        // Take off/landing towards
        assertEquals(3346, obstructed09L_27R.getNewLda(true, runway27R, obstacle),"LDA towards obstacle" );
        assertArrayEquals(new double[] {2986, 2986, 2986}, obstructed09L_27R.getTakeOffDistances(true, runway27R, obstacle), "TORA, TODA, ASDA towards obstacle");
    }

    @Test
    @DisplayName("Heathrow Scenario 2 Calculations")
    public void heathrowScenario2() {

        var obstacle = new Obstacle(25, 75, 34, "Boeing 737-800 (NG)", Obstacle.ObstacleType.AIRCRAFT);
        var obstructed09R_27L = new ObstructedRunway(runway09R, runway27L, -20,500, 2853);

        // Take off/landing towards
        assertEquals(2553, obstructed09R_27L.getNewLda(true, runway09R, obstacle), "LDA towards obstacle");
        assertArrayEquals(new double[] {1850, 1850, 1850}, obstructed09R_27L.getTakeOffDistances(true, runway09R, obstacle), "TORA, TODA, ASDA towards obstacle");

        // Take off/landing away
        assertEquals(1850, obstructed09R_27L.getNewLda(false, runway27L, obstacle),"LDA away from obstacle" );
        assertArrayEquals(new double[] {2860, 2860, 2860}, obstructed09R_27L.getTakeOffDistances(false, runway27L, obstacle), "TORA, TODA, ASDA away from obstacle");
    }

    @Test
    @DisplayName("Obstacle 75 m + from centreline does not affect calculations")
    public void obstacleAwayFromCentreLineTest() {

        var obstacle = new Obstacle(25, 75, 34, "Boeing 737-800 (NG)", Obstacle.ObstacleType.AIRCRAFT);
        var obstructed09R_27L = new ObstructedRunway(runway09R, runway27L, 75,500, 2853);

        // Take off/landing towards
        assertEquals(3353, obstructed09R_27L.getNewLda(true, runway09R, obstacle), "LDA towards obstacle");
        assertArrayEquals(new double[] {3660,3660,3660}, obstructed09R_27L.getTakeOffDistances(true, runway09R, obstacle), "TORA, TODA, ASDA towards obstacle");

        // Take off/landing away
        assertEquals(3660, obstructed09R_27L.getNewLda(false, runway27L, obstacle),"LDA away from obstacle" );
        assertArrayEquals(new double[] {3660,3660,3660}, obstructed09R_27L.getTakeOffDistances(false, runway27L, obstacle), "TORA, TODA, ASDA away from obstacle");

        obstructed09R_27L = new ObstructedRunway(runway09R, runway27L, -75,500, 2853);

        // Take off/landing towards
        assertEquals(3353, obstructed09R_27L.getNewLda(true, runway09R, obstacle), "LDA towards obstacle");
        assertArrayEquals(new double[] {3660,3660,3660}, obstructed09R_27L.getTakeOffDistances(true, runway09R, obstacle), "TORA, TODA, ASDA towards obstacle");

        // Take off/landing away
        assertEquals(3660, obstructed09R_27L.getNewLda(false, runway27L, obstacle),"LDA away from obstacle" );
        assertArrayEquals(new double[] {3660,3660,3660}, obstructed09R_27L.getTakeOffDistances(false, runway27L, obstacle), "TORA, TODA, ASDA away from obstacle");

    }

}
