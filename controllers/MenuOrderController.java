package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import views.SetView;
import views.Frame;
import java.net.URL;
import java.util.ResourceBundle;
/*
 * This class represents the Menu Page Controller
 * @author Ashim Chalise last updated - 03/30/2020
 * 
 * 
 */
public class MenuOrderController {

	public MenuOrderController() {
		
	}
	
	//This method helps the user cash out by navigating to close check window
	@FXML
	void cashOutAction (ActionEvent _event) {
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        stage.close();
        new Frame(new Stage(), SetView.CLOSE_CHECK_VIEW);
	}
	
	//This method helps the user send the order and get started again
	@FXML
	void sendAction(ActionEvent _event) {
		System.out.print("Order sent");
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        stage.close();
        new Frame(new Stage(), SetView.ORDER_TYPE_VIEW);
	}
	
}
	