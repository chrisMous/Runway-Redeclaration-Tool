package uk.ac.soton.comp2211.group37.runwayTool.ui;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.soton.comp2211.group37.runwayTool.App;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MenuWindow code, adapted from the ECSchat Application by Oliver Bills
 */
public class MenuWindow implements Initializable {

    private static final Logger logger = LogManager.getLogger(MenuWindow.class);
    private final App app;

 @FXML
      private TextFlow calculationsBoard;

    @FXML
      private Button calcButton;

    // Scene Graph
    Scene scene = null;
    Parent root = null;

    // Trial code: method used in: getNewRunwayAllocator
    private ImageView runwayAdder;
    Stage stage;
    // 

    public MenuWindow (App app) {

        this.app = app;

        // Load the window with FXML
        try {
            var loader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));

            // Link the GUI in the FXML to this class
            loader.setController(this);
            root = loader.load();

        } catch (Exception e) {
            // Handle exceptions loading FXML
            logger.error("Unable to read file: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        // Set the scene for the window after reading the file
        scene = new Scene(root);

    }

    /**
     * Returns the scene of this window
     * @return this window scene
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Any initial scene setup
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         calculationsBoard.setLineSpacing(3);

         //calls the function that displays the calculations when the "Calculate" button is pressed
         calcButton.setOnAction(this::showCalculations);
    }


  /**
     * Shows all of the calculations in the "Calculations Performed" board on the main screen
     * that display the recalculated values for tora,asda,toda,lda
     * @param event is the event of a mouseClick on the "Calculate" Button
     */
    @FXML
    private void showCalculations(ActionEvent event){
        calculationsBoard.getChildren().clear();

        Text landOver = new Text("09L (Take Off Away, Landing Over) \n");
        Text tora = new Text("TORA = Original TORA - Blast Protection - Distance From Threshold - Displaced Threshold \n");
        Text toraVal1 = new Text("= 3902 - 30 - -50 - 306\n");
        Text toraVal2 = new Text("= 3346\n");
        Text asda = new Text("ASDA = (R) TORA + STOPWAY\n");
        Text asdaVal = new Text("= 3346\n");
        Text toda = new Text("TODA = (R) TORA + CLEARWAY\n");
        Text todaVal = new Text("= 3346\n");
        Text lda = new Text("LDA = Original LDA - Distance From Threshold - Strip End - Slope\n");
        Text ldaVal1 = new Text("= 3595 - -50 -60 - 12*50 \n");
        Text ldaVal2 = new Text ("= 2985 \n");

        Text landTowards = new Text("27R (Take Off Towards, Landing Towards)\n");
        Text tora2 = new Text("TORA = Distance From Threshold - Slope - Strip End \n");
        Text tora2Val1 = new Text("= 3646 - 60 -12*50 \n");
        Text tora2Val2 = new Text("= 3646\n");
        Text asda2 = new Text("ASDA = (R) TORA \n");
        Text asda2Val = new Text ("= 2986 \n");
        Text toda2 = new Text("TODA = (R) TORA \n");
        Text toda2Val = new Text ("= 2986 \n");
        Text lda2 = new Text("LDA =Distance From Threshold - RESA - Strip End \n");
        Text lda2Val1 = new Text("= 3646 -240 - 60 \n");
        Text lda2Val2 = new Text("= 3346");

        ArrayList<Text> textList = new ArrayList<>();
        textList.add(landOver);
        textList.add(tora);
        textList.add(toraVal1);
        textList.add(toraVal2);
        textList.add(asda);
        textList.add(asdaVal);
        textList.add(toda);
        textList.add(todaVal);
        textList.add(lda);
        textList.add(ldaVal1);
        textList.add(ldaVal2);
        textList.add(landTowards);
        textList.add(tora2);
        textList.add(tora2Val1);
        textList.add(tora2Val2);
        textList.add(asda2);
        textList.add(asda2Val);
        textList.add(toda2);
        textList.add(toda2Val);
        textList.add(lda2);
        textList.add(lda2Val1);
        textList.add(lda2Val2);
        
        for(Text text : textList){
            text.setStyle("-fx-font-size: 12px");
            calculationsBoard.getChildren().add(text);

        }
        landOver.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");
        landTowards.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");

    }
    // This is the trial code for connecting the menu scene with the newRunwayAllocatorScene along with
    public void getNewRunwayAllocatorScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/NewRunwayAllocator.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
