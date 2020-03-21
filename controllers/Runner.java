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

public class Runner extends Application {

	/**
	 * Called when the software starts.
	 *
	 * @param primaryStage
	 * @throws Exception
	 */
	public void start(Stage primaryStage) throws Exception {

		// Creating a views.Frame object with log in view as our main view
		Frame frame = new Frame(primaryStage, SetView.LOG_IN_VIEW);
	}

	/**
	 * Called when the application launches.
	 *
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		launch(args);
//		GoogleAuthWrapper googleAuth = new GoogleAuthWrapper(8000,
//				"297008996238-d2fmttvlbl4mnetvgf7837okn7squj1e.apps.googleusercontent.com", "XESnLdvdh2e6Eiege72NgEtO");
//
//		try {
//			googleAuth.getUserInfo().forEach((k, v) -> System.out.println(k + " : " + v));
//		} catch (OAuthException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			googleAuth.getUserInfo().forEach((k, v) -> System.out.println(k + " : " + v));
//		} catch (OAuthException e) {
//			e.printStackTrace();
//		}
	}

}