package uk.ac.soton.comp2211.group37.runwayTool.model;

import java.io.Serializable;

/**
 * A physical runway consisting of two logical runways at an aerodrome.
 */
public class PhysicalRunway implements Serializable {

    protected LogicalRunway logicalRunway1;
    protected LogicalRunway logicalRunway2;

    public PhysicalRunway(LogicalRunway rwy1, LogicalRunway rwy2) {
        this.logicalRunway1 = rwy1;
        this.logicalRunway2 = rwy2;
    }

}
