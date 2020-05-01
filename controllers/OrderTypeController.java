package controllers;

/**
 * This class controls Order Type view.
 *
 * @author Sultan Al Bogami Last Updated: 2/22/2020
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import models.Table;
import views.SetView;
import views.Frame;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;

public class OrderTypeController implements Initializable {

	protected ArrayList<Table> restaurantTables;
	@FXML
	private TextField username;

	public OrderTypeController() {
	}

	/**
	 * Called once implementing the controller.
	 *
	 * @author raiana zaman last update 04/14/2010 it should show username from
	 *         logInController
	 */
	public void initialize(URL _url, ResourceBundle _rb) {
	}

	/**
	 * Called when dine in button is clicked.
	 *
	 * @param _event
	 */
	@FXML
	private void dineInAction(ActionEvent _event) {
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
		stage.close();
		new Frame(new Stage(), SetView.MENU_ORDER_VIEW);
	}

	/**
	 * Called when take out button is clicked.
	 *
	 * @param _event
	 */
	@FXML
	private void takeOutAction(ActionEvent _event) {
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
		stage.close();
		new Frame(new Stage(), SetView.MENU_ORDER_VIEW);
	}

	/**
	 * Function to get userinfo
	 */
	public void myFunction(String text) {
		username.setText(text);
	}
}
