package uk.ac.soton.comp2211.group37.runwayTool;

/**
 * The launcher class allows us to package up the main app into a shaded Jarfile. This class is run when using the shaded jarfile.
 */
public class Launcher {

    /**
     * Call the main method of the App class.
     *
     * @param args the arguments passed on the command line
     */
    public static void main(String[] args) {

        App.main(args);

    }

}
