package uk.ac.soton.comp2211.group37.runwayTool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.soton.comp2211.group37.runwayTool.ui.MenuWindow;

/**
 * JavaFX main App class
 * @authors cb7g20
 */
public class App extends Application {

    private static final Logger logger = LogManager.getLogger(App.class);
    private Stage stage;

    /**
     * Launch the Application
     * @param args the arguments to launch with
     */
    public static void main(String[] args) {
        logger.info("Starting Client");
        launch();
    }

    /**
     * Start the JavaFX process and show the menu window
     * @param stage
     */
    @Override
    public void start(Stage stage) {

        this.stage = stage;
        stage.setTitle("Runway Redeclaration Tool");
        stage.setOnCloseRequest(ev -> shutdown());

        // Initial Window
//        var pane = new BorderPane();
//        var scene = new Scene(pane, 640, 480);
//        var label = new Label("Runway Redeclaration Tool");
//        pane.setCenter(label);


        // Stage setup
//        stage.setScene(scene);
//        stage.show();

        openMenu();
    }

    /**
     * Display the menu window
     */
    public void openMenu() {
        logger.info("Opening the menu window");
        var window = new MenuWindow(this);
        stage.setScene(window.getScene());
        stage.show();
        stage.centerOnScreen();
    }

    public void shutdown() {
        logger.info("Closing Application");
        System.exit(0);
    }
}
