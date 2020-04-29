package controllers;

/**
 * This class starts up our application with the LogInWindow.fxml.
 *
 * @author Sultan Al Bogami Last Updated: 2/22/2020
 */

import java.io.IOException;

import API.GoogleAuthWrapper;
import API.OAuthException;
import javafx.application.Application;
import javafx.stage.Stage;
import views.Frame;
import views.SetView;

//TODO unused imports
import java.awt.Color;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;

public class Runner extends Application {

	/**
	* Called when the software starts.
	*
	* @param primaryStage
	* @throws Exception
	*/
	public void start(Stage primaryStage) throws Exception {
		Frame frame = new Frame(primaryStage, SetView.LOG_IN_VIEW);
	}

	/**
	* Called when the application launches.
	*
	* @param args
	*/
	public static void main(String[] args) throws IOException {
		launch(args);
		//Application.launch(args);
	}

}