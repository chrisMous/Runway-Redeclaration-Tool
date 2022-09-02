package uk.ac.soton.comp2211.group37.runwayTool.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ObstructedRunway extends PhysicalRunway {
    /**
     * The revised TORA (Take-Off Run Available) when an obstacle is in a runway's vicinity.
     */
    double newTora;

    /**
     * The revisesd TODA (Take-Off Distance Available) when an obstacle is in a runway's vicinity.
     */
    double newToda;

    /**
     * The revised ASDA (Accelerate Stop Distance Available) when an obstacle is in a runway's vicinity.
     */
    double newAsda;

    /**
     * The revised LDA (Landing Distance Available) when an obstacle is in a runway's vicinity.
     */
    double newLda;

    /**
     * The distance of the Obstacle from the centreline of the runway.
     */
    double distanceFromCentre;

    /**
     * The distance of the Obstacle from the Right Threshold of the runway.
     */
    double distanceRightThreshold;

    /**
     * The distance of the Obstacle from the Left Threshold of the runway.
     */
    double distanceLeftThreshold;

    /**
     * Array contains the new TakeOff Distances: TORA, TODA, ASDA
     */
    double[] takeOffDistances = new double[3];
    private final Logger logger = LogManager.getLogger(ObstructedRunway.class);

    /**
     * Creates a new Runway with the obstacle on it/ near it.
     * @param rwy1 One of the ends of the physical runway
     * @param rwy2 One of the ends of the physical runway 180 degrees opposite to the above end
     * @param distanceFromCentre Distance of the object from the centreline of the runway
     * @param distanceLeftThreshold Distance of the object from the left threshold
     * @param distanceRightThreshold Distance of the object from the right threshold
     * */

    public ObstructedRunway(LogicalRunway rwy1, LogicalRunway rwy2,
                            double distanceFromCentre, double distanceLeftThreshold, double distanceRightThreshold) {
        super(rwy1, rwy2);
        this.distanceFromCentre = distanceFromCentre;
        this.distanceLeftThreshold = distanceLeftThreshold;
        this.distanceRightThreshold = distanceRightThreshold;
    }

    /**
     * This method returns the distance of the obstacle from the centreline of the runway
     */

    public double getDistanceFromCentre() {
        return distanceFromCentre;
    }

    /**
     * This method returns the distance of the obstacle from the left threshold of the runway
     */

    public double getDistanceLeftThreshold() {
        return distanceLeftThreshold;
    }

    /**
     * This method returns the distance of the obstacle from the right threshold of the runway
     */

    public double getDistanceRightThreshold() {
        return distanceRightThreshold;
    }

    /**
     * This method returns the revised Landing Distance Available when an obstacle is either on the runway or 75 metres north/south from its centreline.
     * @param landingTowardsObstacle Boolean value, whether the aircraft is landing towards the obstacle or landing over the obstacle
     * @param logicalRunway Object of the LogicalRunway class, holds the runway's specifications: TORA, TODA, ASDA, LDA, Displaced Threshold
     * @param obstacle Object of the Obstacle class which has the obstacle's characteristics: Height, width, Length.
     */
    public double getNewLda (Boolean landingTowardsObstacle, LogicalRunway logicalRunway, Obstacle obstacle) {
        // Only recalculate if the obstacle is within 75 m of the runway
        if ((getDistanceFromCentre() < 75 && getDistanceFromCentre() > (-75))) {
            if (landingTowardsObstacle) {
                if (getDistanceRightThreshold() >= (0.5 * logicalRunway.getLda())) {
                    newLda = getDistanceRightThreshold() - logicalRunway.getResa() - logicalRunway.getStripEnd();
                } else if (getDistanceLeftThreshold() >= (0.5 * logicalRunway.getLda())){
                    newLda = getDistanceLeftThreshold() - logicalRunway.getResa() - logicalRunway.getStripEnd();
                }

            // Not landing towards the obstacle
            } else if (obstacle.getBase() > logicalRunway.getResa()) {
                if (getDistanceRightThreshold() >= (0.5 * logicalRunway.getLda())) {
                    newLda = logicalRunway.getLda() - obstacle.getBase() - logicalRunway.getStripEnd() - logicalRunway.getDisplacedThreshold() - getDistanceLeftThreshold();
                } else {
                    newLda = logicalRunway.getLda() - obstacle.getBase() - logicalRunway.getStripEnd() - logicalRunway.getDisplacedThreshold() - getDistanceRightThreshold();
                }
            } else if (obstacle.getBase() <= logicalRunway.getResa()) {
                if (getDistanceRightThreshold() >= (0.5 * logicalRunway.getLda())) {
                    newLda = logicalRunway.getLda() - logicalRunway.getResa() - logicalRunway.getStripEnd() - logicalRunway.getDisplacedThreshold() - getDistanceLeftThreshold();
                } else {
                    newLda = logicalRunway.getLda() - logicalRunway.getResa() - logicalRunway.getStripEnd() - logicalRunway.getDisplacedThreshold() - getDistanceRightThreshold();
                }
            }
            return newLda;
        }
        return logicalRunway.getLda();
    }

    /**
     * This method returns the revised TORA, TODA, ASDA when an obstacle is either on the runway or within in 75 metres North/South from its centreline.
     * @param takingOffTowardsObstacle Boolean value, whether the aircraft is taking off towards the obstacle.
     * @param logicalRunway Object of the LogicalRunway class, holds the runway's specifications: TORA, TODA, ASDA, LDA, Displaced Threshold.
     * @param obstacle Object of the Obstacle class which has the obstacle's characteristics: Height, width, Length.
     */

    public double[] getTakeOffDistances (Boolean takingOffTowardsObstacle, LogicalRunway logicalRunway, Obstacle obstacle) {
        if ((getDistanceFromCentre() < 75 && getDistanceFromCentre() > (-75))) {
            if (!takingOffTowardsObstacle) {
                logger.debug("Not taking off towards the obstacle");
                if (getDistanceLeftThreshold() < (0.5 * logicalRunway.getTora())) {

                    newTora = logicalRunway.getTora() + logicalRunway.getDisplacedThreshold() - logicalRunway.getBlastProtection() - getDistanceLeftThreshold();
                    newToda = logicalRunway.getToda() + logicalRunway.getDisplacedThreshold() - logicalRunway.getBlastProtection() - getDistanceLeftThreshold();
                    newAsda = logicalRunway.getAsda() + logicalRunway.getDisplacedThreshold() - logicalRunway.getBlastProtection() - getDistanceLeftThreshold();
                } else {
                    newTora = logicalRunway.getTora() - logicalRunway.getDisplacedThreshold() - logicalRunway.getBlastProtection() - getDistanceRightThreshold();
                    newToda = logicalRunway.getToda() - logicalRunway.getDisplacedThreshold() - logicalRunway.getBlastProtection() - getDistanceRightThreshold();
                    newAsda = logicalRunway.getAsda() - logicalRunway.getDisplacedThreshold() - logicalRunway.getBlastProtection() - getDistanceRightThreshold();
                }
                takeOffDistances[0] = newTora;
                takeOffDistances[1] = newToda;
                takeOffDistances [2] = newAsda;
                return takeOffDistances;
            } else if (obstacle.getBase() > logicalRunway.getResa()) {
                if (getDistanceLeftThreshold() < (0.5 * logicalRunway.getTora())) {
                    newTora = getDistanceRightThreshold() + logicalRunway.getDisplacedThreshold() - obstacle.getBase() - logicalRunway.getStripEnd();
                    newToda = getDistanceRightThreshold() + logicalRunway.getDisplacedThreshold() - obstacle.getBase() - logicalRunway.getStripEnd();
                    newAsda = getDistanceRightThreshold() + logicalRunway.getDisplacedThreshold() - obstacle.getBase() - logicalRunway.getStripEnd();
                } else {
                    newTora = getDistanceLeftThreshold() + logicalRunway.getDisplacedThreshold() - obstacle.getBase() - logicalRunway.getStripEnd();
                    newAsda = getDistanceLeftThreshold() + logicalRunway.getDisplacedThreshold() - obstacle.getBase() - logicalRunway.getStripEnd();
                    newToda = getDistanceLeftThreshold() + logicalRunway.getDisplacedThreshold() - obstacle.getBase() - logicalRunway.getStripEnd();
                }
                takeOffDistances[0] = newTora;
                takeOffDistances[1] = newToda;
                takeOffDistances [2] = newAsda;
                return takeOffDistances;
            } else if (obstacle.getBase() <= logicalRunway.getResa()) {
                if (getDistanceLeftThreshold() < (0.5 * logicalRunway.getTora())) {
                    newTora = logicalRunway.getTora() + logicalRunway.getDisplacedThreshold() - getDistanceLeftThreshold() - logicalRunway.getResa() - logicalRunway.getStripEnd();
                    newToda = logicalRunway.getToda() + logicalRunway.getDisplacedThreshold() - getDistanceLeftThreshold() - logicalRunway.getResa() - logicalRunway.getStripEnd();
                    newAsda = logicalRunway.getAsda() + logicalRunway.getDisplacedThreshold() - getDistanceLeftThreshold() - logicalRunway.getResa() - logicalRunway.getStripEnd();
                } else {
                    newTora = getDistanceLeftThreshold() + logicalRunway.getDisplacedThreshold() - logicalRunway.getResa() - logicalRunway.getStripEnd();
                    newToda = getDistanceLeftThreshold() + logicalRunway.getDisplacedThreshold() - logicalRunway.getResa() - logicalRunway.getStripEnd();
                    newAsda = getDistanceLeftThreshold() + logicalRunway.getDisplacedThreshold() - logicalRunway.getResa() - logicalRunway.getStripEnd();
                }
                takeOffDistances[0] = newTora;
                takeOffDistances[1] = newToda;
                takeOffDistances [2] = newAsda;
                return takeOffDistances;
            }
        }
        takeOffDistances[0] = logicalRunway.getTora();
        takeOffDistances[1] = logicalRunway.getToda();
        takeOffDistances [2] = logicalRunway.getAsda();
        return takeOffDistances;
    }
}
