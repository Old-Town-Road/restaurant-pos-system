package controllers;

/**
 * This class controls log in view.
 *
 * @author Sultan Al Bogami Last Updated: 2/22/2020
 * @author Raiana zaman Last Updated: 3/18/2020
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import views.SetView;
import views.Frame;
import java.io.IOException;
import java.util.Map;
import java.net.URL;
import java.util.ResourceBundle;
import API.GoogleAuthImpl;
import API.GoogleAuthWrapper;
import API.IGoogleAuth;
import API.OAuthException;

public class LogInController implements Initializable {

	GoogleAuthWrapper google;

	public LogInController() throws IOException {

		google = new GoogleAuthWrapper(8000, "297008996238-d2fmttvlbl4mnetvgf7837okn7squj1e.apps.googleusercontent.com",
				"XESnLdvdh2e6Eiege72NgEtO");
	}

	/**
	 * Called once implementing the controller.
	 *
	 * @param _url
	 * @param _rb
	 */

	public void initialize(URL _url, ResourceBundle _rb) {
	}

	/**
	 * Called when log in button is clicked.
	 *
	 * @param _event
	 * @throws OAuthException
	 * @throws IOException
	 */
	// @FXML
	// private void glogInAction(ActionEvent _event) throws IOException,
	// OAuthException {
	// Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
	// stage.close();
	// new Frame(new Stage(), SetView.ORDER_TYPE_VIEW);
	// }

	@FXML
	private void logInAction(ActionEvent _event) throws IOException, OAuthException {
		System.out.println("erorr");
		// Map<String, String> userInfo = google.getUserInfo();
		try {
			Map<String, String> userInfo = google.getUserInfo();
			Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
			stage.close();
			new Frame(new Stage(), SetView.ORDER_TYPE_VIEW);
		} catch (IOException | OAuthException e) {
			e.printStackTrace();
		}
		System.out.println("erorr");
	}
}