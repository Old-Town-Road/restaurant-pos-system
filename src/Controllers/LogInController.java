package Controllers;

/**
 * This class controls log in view.
 *
 * @author Sultan Al Bogami Last Updated: 2/22/2020
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import views.SetView;
import views.Frame;
import java.net.URL;
import java.util.ResourceBundle;
// testing connection -Raiana
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//testing connection -Raiana

import helpers.DatabaseHelper;

public class LogInController implements Initializable {
	// raiana
	@FXML
	private TextField usernameText;
	@FXML
	private PasswordField passwordText;
	@FXML
	public Button login;
	private DatabaseHelper dbHelper = new DatabaseHelper();
	
  


	public LogInController() {
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
	 */
	
	@FXML //Raiana
	private void logInAction(ActionEvent _event) throws SQLException {
		DatabaseHelper connectionClass=new DatabaseHelper();
        Connection connection=connectionClass.getConnection();
        
        Statement statement=connection.createStatement();
		String sql="SELECT * FROM userauth WHERE username = '"+usernameText.getText()+"' AND password = '"+passwordText.getText()+"';";
		statement.executeQuery(sql);
		
		
//		boolean authenticated = dbHelper.authenticateUser(usernameText.getText(), passwordText.getText());
//		if (authenticated) {
//			System.out.println("authenticated");
//			Stage stage = (Stage) ((Node) _event.getSource()).getScene().getWindow();
//			stage.close();
//			new Frame(new Stage(), SetView.ORDER_TYPE_VIEW);
//		} else {
//			Alert alert = new Alert(AlertType.WARNING);
//			alert.setTitle("Warning Dialog");
//			alert.setHeaderText("Error:");
//			alert.setContentText("Wrong login information.\nPlease try again.");
//			alert.showAndWait();
//		}
	}
	
	
	@FXML
	private void handleUsername(MouseEvent event) {
//	    usernameText.setText("");
	    usernameText.setFocusTraversable(true);
	    passwordText.setFocusTraversable(true);
	}
	
	@FXML
	private void handlePassword(MouseEvent event) {
//	    passwordText.setText("");
	    usernameText.setFocusTraversable(true);
	    passwordText.setFocusTraversable(true);
	}

}
