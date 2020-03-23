package controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import views.Frame;
import views.SetView;


public class MenuOrderController {//implements Initializable {

	public MenuOrderController() {
		
	}
	
	
	private void MenuOrderAction(ActionEvent _event) {
		Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
        stage.close();
        new Frame(new Stage(), SetView.CLOSE_CHECK_VIEW);
    }
	}