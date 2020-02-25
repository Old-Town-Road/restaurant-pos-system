package Controllers;

/**
 * This class controls CloseCheck view.
 *
 * @author Raiana Zaman Last Updated: 2/24/2020
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import views.SetView;
import views.Frame;
import java.net.URL;
import java.util.ResourceBundle;

public class CloseCheckController implements Initializable {

	public CloseCheckController() {
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
	 * Called when cash button is clicked.
	 *
	 * @param _event
	 */
	@FXML
	private void cashAction(ActionEvent _event) {
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
		stage.close();
		new Frame(new Stage(), SetView.ORDER_TYPE_VIEW);
	}

	/**
	 * 
	 * Called when creditCard button is clicked.
	 *
	 * @param _event
	 */
	@FXML
	private void creditAction(ActionEvent _event) {
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
		stage.close();
		new Frame(new Stage(), SetView.ORDER_TYPE_VIEW);
	}

	/**
	 * Called when other button is clicked.
	 *
	 * @param _event
	 */
	@FXML
	private void otherAction(ActionEvent _event) {
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
		stage.close();
		new Frame(new Stage(), SetView.ORDER_TYPE_VIEW);
	}
}
