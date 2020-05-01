package controllers;

/**
 * This class starts up our application with the LogInWindow.fxml.
 *
 * @author Sultan Al Bogami Last Updated: 01/05/2020
 * <p>
 * Notes:
 * -> Made sure the style-guide is followed
 */

import javafx.application.Application;
import javafx.stage.Stage;
import views.Frame;
import views.SetView;

import java.io.IOException;

public class Runner extends Application {

    /**
     * Called when the software starts.
     *
     * @param primaryStage from which the application start.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Call the frame to start
        Frame frame = new Frame(primaryStage, SetView.TABLE_SELECTION_VIEW);
    }

    /**
     * Called when the application launches.
     *
     * @param args a string array of arguments accepted to start the application.
	 * @throws IOException in case file is not found.
     */
    public static void main(String[] args) throws IOException {
        Application.launch(args);
    }
}