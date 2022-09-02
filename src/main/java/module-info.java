module uk.ac.soton.comp2211.group37.runwayTool {
    requires javafx.controls;
    requires java.scripting;
    requires javafx.fxml;
    requires javafx.media;
    requires org.apache.logging.log4j;
    requires java.xml;
    opens uk.ac.soton.comp2211.group37.runwayTool.ui to javafx.fxml;
    exports uk.ac.soton.comp2211.group37.runwayTool;
}
