package uk.ac.soton.comp2211.group37.runwayTool.model;

/**
 * A logical runway as part of a physical runway
 */
public class LogicalRunway {

    /**
     * The default TORA (Take-Off Run Available) when there is no obstacle on the runway.
     */
    double tora;

    /**
     * The default TODA (Take-Off Distance Available) when there is no obstacle on the runway.
     */
    double toda; // Take-Off Distance Available

    /**
     * The default ASDA (Accelerate Stop Distance Available) when there is no obstacle on the runway.
     */
    double asda;

    /**
     * The default LDA (Landing Distance Available) when there is no obstacle on the runway.
     */
    double lda;

    /**
     * The distance by which the Threshold has been displaced, note: can be 0 for runways, and can be used for take-off.
     */
    double displacedThreshold;

    /**
     * Clearway area around the runway.
     */
    double clearway; // Clearway

    /**
     * Area beyond the end of the TORA, which may be used in the case of an abandoned take-off so that an aircraft can be safely brought to	a stop.
     */
    double stopway;

    /**
     * Area symmetric to the runway centreline declared from the obstacle's base, used for preventing an aircraft undershooting/overshooting the runway.
     */
    final static double RESA = 240;

    /**
     * An area between the end of the runway and the end of the runway strip.
     */
    final static double STRIP_END = 60;

    /**
     * Area used for preventing engine blast from affecting any obstacles behind it.
     */
    final static double BLAST_PROTECTION = 300;

    /**
     * Hypotenuse formed between top of the obstacle and airport's minimum angle of descent.
     */
    double als;

    /**
     * Hypotenuse formed between top of the obstacle and airport's minimum angle of ascent.
     */
    double tocs;

    /**
     * Area enclosing runway and any associated stopway.
     */
    double runwayStrip;

    /**
     * Angle of the runway
     */
    int heading;

    private RunwayPosition position;

    public enum RunwayPosition {
        RIGHT,
        CENTER,
        LEFT,
        NONE
    }

    /**
     * Used for declaring runway specifications
     * @param tora The TORA of the runway without the obstacle
     * @param toda The TODA of the runway without the obstacle
     * @param asda The ASDA of the runway without the obstacle
     * @param lda The LDA of the runway without the obstacle
     * @param displacedThreshold The distance by which the Threshold of the runway has been shifted by.
     */

    public LogicalRunway(double tora, double toda, double asda, double lda, double displacedThreshold, int heading, RunwayPosition position) {
        this.tora = tora;
        this.toda = toda;
        this.asda = asda;
        this.lda = lda;
        this.displacedThreshold = displacedThreshold;
        this.heading = heading;
        this.position = position;
    }

    /**
     * This method returns the original TORA of the created runway.
     */

    public double getTora(){
        return tora;
    }

    /**
     * This method returns the original TODA of the created runway.
     */

    public double getToda(){
        return toda;
    }

    /**
     * This method returns the original ASDA of the created runway.
     */

    public double getAsda(){
        return asda;
    }

    /**
     * This method returns the original LDA of the created runway.
     */

    public double getLda(){
        return lda;
    }

    /**
     * This method returns the distance by which the Threshold of the runway has been shifted by.
     */

    public double getDisplacedThreshold(){
        return displacedThreshold;
    }

    /**
     * This method returns the Clearway area from the runway.
     */

    public double getClearway(){
        return clearway;
    }

    /**
     * This method returns the Stopway area from the end TORA of the created runway.
     */

    public double getStopway(){
        return stopway;
    }

    /**
     * This method returns the constant value RESA called on any runway.
     */

    public double getResa(){
        return RESA;
    }

    /**
     * This method returns the constant value Strip End called on any runway.
     */

    public double getStripEnd(){
        return STRIP_END;
    }

    /**
     * This method returns the constant value Blast Protection from the aircraft, called on any runway.
     */

    public double getBlastProtection(){
        return BLAST_PROTECTION;
    }

    /**
     * This method returns the ALS approach that an aircraft follows while landing on a runway over an obstacle.
     */

    public double getAls(){
        return als;
    }

    /**
     * This method returns the TOCS rate that an aircraft follows while taking off from a runway over an obstacle
     */

    public double getTocs(){
        return tocs;
    }

    /**
     * This method returns the area enclosing runway along with its associated stopway.
     */

    public double getRunwayStrip(){
        return runwayStrip;
    }

}
